package controllers;

import api.auth.Auth;
import api.util.DateFormat;
import app_bizz.AppPlans;
import data_services.signup.AdminStaffEnroll;
import data_services.signup.AdminSignUp;
import data_services.Login;
import models.enum_config.OnlineStatus;
import models.enum_config.Roles;
import models.enum_config.Status;
import models.Users;
import models.education.School;
import play.data.DynamicForm;
import play.data.*;
import play.mvc.*;

import views.html.*;
//import play.filters.csrf.RequireCSRFCheck;
import javax.inject.*;
import java.util.Date;

public class HomeCtr extends QusiController {
    private final AdminStaffEnroll adminReg;
    private final AdminSignUp newSub;
    private final Login login;
    private boolean checkResult = false;
    private FormFactory formFactory;

    @Inject
    public HomeCtr(AdminSignUp adminSignUp, AdminStaffEnroll adminReg , Login login,FormFactory formFactory ){
        this.newSub = adminSignUp;
        this.formFactory = formFactory;
        this.adminReg = adminReg;
        this.login = login;
    }



    // public AdminSignUp newSub = null;
    // landing page
    public Result index() {
       return ok(index.render());
    }
    // signUp with sub_domain name and email for the first time
    public Result postSignUp(){
        Form<AdminSignUp> newSubscriberForm = formFactory.form(AdminSignUp.class);

        if(newSubscriberForm.hasErrors()){
            flash().put("error","Invalid input field");
            return redirect(routes.HomeCtr.index());
        }
        AdminSignUp adminSignUp = newSubscriberForm.bindFromRequest().get();
        if(adminSignUp == null){
            flash().put("error","The Fields input Can't be empty");
            return redirect(routes.HomeCtr.index());

        }
        if(adminSignUp.email == null && adminSignUp.subDomain == null){
            flash().put("error","Domain name and email Can't be empty");
            return redirect(routes.HomeCtr.index());
        }
        if(adminSignUp.teamsAndConditions == null ){
            flash().put("error","You have to agree with the term and condition by check the box");
            return redirect(routes.HomeCtr.index());
        }

        System.out.println("Domain: "+ adminSignUp.subDomain);
        System.out.println();
        System.out.println("Email: "+ adminSignUp.email);
        System.out.println();
        System.out.println("Teams And Conditions: "+ adminSignUp.teamsAndConditions);


        Users  users = newSub.signUpEmailVerify(adminSignUp);
        School school =  newSub.signUpSubDomainVerify(adminSignUp);

        if(users != null || school != null){
            if(adminSignUp.subDomain.equals(school.subDomain) && adminSignUp.email.equals(users.userEmail)) {
                System.out.println("SubDomain: "+school.subDomain);
                if (users.status.equals(Status.Pending.getValue())) {
                    System.out.println("Status: "+Status.Pending.getValue());
                    flash().put("info", "Your are not fully register. please reverence back to sent email by QUSI to complete your registration.");
                    return redirect(routes.HomeCtr.index());
                } else if (school.status.equals(Status.InActivate.getValue())) {
                    System.out.println("App Access : "+Status.InActivate.getValue());
                    flash().put("error", "Your account has been deactivated. Please contact administrator for help.");
                    return redirect(routes.HomeCtr.loginPage());
                }else if (school.status.equals(Status.Demo.getValue()) || school.status.equals(Status.Activate.getValue())) {
                    System.out.println("App Access : "+Status.Demo.getValue());
                    System.out.println("App Access : "+Status.Activate.getValue());
                    flash().put("info", "Login through your home page.");
                    return redirect(routes.HomeCtr.loginPage());
                }

            }else if(adminSignUp.subDomain.equals(school.subDomain) || adminSignUp.email.equals(users.userEmail)) {
                flash().put("error", "One of this details is existing. Please try new details");
                return redirect(routes.HomeCtr.index());
            }
        }/// also check school if is null 3j4NU9k

        // add the session once, add email link to confirmation page
        // and send the confirmation code,sub-domain with hash code and expiring date to email
        // the token will be validate with hash code if the giving time is not expire
        // save plane value into browser local storage
        // and after registration from complete add the plan to the form value

        checkResult = newSub.userInsert(adminSignUp);
        if(!checkResult){
            System.out.println("Result: "+checkResult);
            flash().put("error", "SignUp field please try again");
            return redirect(routes.HomeCtr.index());
        }
        //sessionReg(adminSignUp.email);
        flash().put("success","Welcome To quick school information");
      return ok(views.html.signup.signup_successful.render(adminSignUp.subDomain,adminSignUp.email));
    }

  /*  public Result successSignUp(){
        return ok(views.html.signup.signup_successful.render());
    }*/

    // confirm reference token code from email
    public Result confirmEmailToken(String userEmail){
        signUpSession(userEmail);
        flash().put("success","Welcome to quick school information");
        return ok(views.html.signup.signup_confirmation.render());
    }

    // generate token and send it to client email
    public Result tokenCodeForm(){
        Form<AdminStaffEnroll> tokenCodeForm = Form.form(AdminStaffEnroll.class).bindFromRequest();
        if(tokenCodeForm.hasErrors()){
            flash().put("error","Invalid Input");
            return badRequest(views.html.signup.signup_confirmation.render());
        }
        AdminStaffEnroll adminStaffEnroll = tokenCodeForm.get();

        if(newSub.findTokenCode(adminStaffEnroll.comfirmCode)!= true){
            flash().put("error","Enter Wrong Token Code");
             return badRequest(views.html.signup.signup_confirmation.render());
        }
            flash().put("success","Your token code is valued");
            return ok(views.html.signup.plane_price.render());
    }

    public Result pricePlane(){
        String plan ="";
        // use the url for mail to fresh information in db
        Form<AdminStaffEnroll> pricePlane = Form.form(AdminStaffEnroll.class).bindFromRequest();
        if(pricePlane.hasErrors()){
            flash().put("error","Invalid Input");
        }
        AdminStaffEnroll adminStaffEnroll = pricePlane.get();

        String btnAction = DynamicForm.form().bindFromRequest().get("action");
        if(btnAction.equals("Basic")){
            plan = "basic";
        }
        if(btnAction.equals("Starter")){
            plan = "starter";
        }
        if(btnAction.equals("Professional")){
            plan = "professional";
        }
        if(btnAction.equals("Unlimited")){
            plan = "unlimited";
        }
      //  sessionAdd(adminStaffEnroll.user_email);
        adminStaffEnroll.plan = plan;

            System.out.println(plan);
        adminReg.addPlan(adminStaffEnroll);
        // fee_master it database

        flash().put("success","You successfully Subscribe to "+plan+" Plan");
        return ok(views.html.signup.admin_reg.render());
    }
    // school info, completing registration
    public Result adminRegistration(){
        Form<AdminStaffEnroll> adminRegForm = Form.form(AdminStaffEnroll.class).bindFromRequest();
        if(adminRegForm.hasErrors()){
            flash().put("error","Invalid input field");
            return badRequest(views.html.signup.admin_reg.render());
        }
        AdminStaffEnroll adminStaffEnroll = adminRegForm.get();
        if (!adminStaffEnroll.re_password.equals(adminStaffEnroll.password)){
            flash().put("error","Password Not Match");
            return badRequest(views.html.signup.admin_reg.render());
        }

        adminReg.regAdminInfoAndSchoolInfo(adminStaffEnroll);
        System.out.println("User Email: "+Auth.sessionEmail());
        signInSession(Auth.sessionEmail());
        createLogSession(Auth.sessionUsersId(),Roles.SUPPER_ADMIN.getValue(),OnlineStatus.Online.getValue());

        flash().put("success","Congratulation you successfully register");
        return redirect(routes.HomeCtr.dashboard());
    }
    // admin home page
    public Result dashboard(){
        cookies();
        return ok(views.html.authpage.dashboard.render());
    }

    public Result pageDashboard(){
        return ok(views.html.authpage.dashboard.render());
    }
    // first adding session on signUp
    public void signUpSession(String query){
        Users users = new Users();

        users = newSub.userInfo("{userEmail: '"+query+"'}");
        System.out.println("The query School: "+users.schoolId);

        session().put("connected","true");
        session().put("email",users.userEmail);
        session().put("id",String.valueOf(users._id));
        session().put("school_id",users.schoolId);
    }


    // second adding session on complete registration
    public void signInSession(String query){
        Users users = new Users();
        School school = new School();
        System.out.println("Users Email: "+query);
        //Long.parseLong(s)
        users = newSub.userInfo("{userEmail: '"+query+"'}");
        if(users == null){
            System.out.println("Yes Is Empty");
        }
        System.out.println("School Id: "+users.schoolId);
        school = adminReg.schoolInfo(users.schoolId);

        System.out.println("users role : "+users.role);
        System.out.println("users status : "+users.status);

        System.out.println("\n email"+users.userEmail +" id: "+String.valueOf(users._id)+" school_id: "+users.schoolId +" plan: "+school.appPlanePackage +" school_name: "+school.name +" role: "+users.role +" status: "+users.status);

        session().put("connected","true");
        session().put("email",users.userEmail);
        session().put("id",String.valueOf(users._id));
        /*session().put("school_branch",users.school_branch);*/
        session().put("school_id",users.schoolId);
        session().put("plan",school.appPlanePackage);
        session().put("school_name",school.name);
        session().put("role",users.role);
        session().put("status",users.status);
        if(!school.abbreviation.isEmpty()){
            session().put("abbreviation",school.abbreviation);
        }
    }

    // login from sub_domain page
    // check if auto delete session only it get expire by time giving
    // check if the sub domain exist in session collection if yes incese the login number and mail the user of new login info
    // or no, get all info of the browser and the device
    //and save it in session collection

    public Result login(){

        String path = request().path();
        String host = request().host();

        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        if(loginForm.hasErrors()){
            flash().put("error","Invalid Input");
        }

        System.out.println("\n PATH: "+ path);
        System.out.println("\n HOST: "+ host);

        // use the path to query the database for url extension

        Login log = loginForm.get();
        /*if(host.toLowerCase().contains(".localhost")){
            host = host.split(".localhost")[1];
        }*/
        System.out.println("\n Email: "+ log.user_email);
        System.out.println("\n Password: "+ log.password);

        Users user = login.authenticate(log);
        if(user != null){
            School school = new School();
            if(user.status.equals(Status.Activate.getValue())){
                String isOnSession ="";
                isOnSession  = session().getOrDefault("email",user.userEmail);
                System.out.println("Value: "+ isOnSession);
                /*if(isOnSession.equals(user.user_email)){
                    flash().put("error"," The user is already on a session, You have to logout before you can login ");
                    return redirect(routes.HomeCtr.loginPage());
                }*/
                // check the school status if its on demo or active
                 school = adminReg.schoolInfo(user.schoolId);
                if(school.status.equals(Status.Demo.getValue())){
                    if(!AppPlans.demoValidate(DateFormat.getDifferenceDays(new Date(),school.modifierDate))){
                        flash().put("error","Your account demo have expiry Please make a payment, ");
                        //return to a payment page
                        return redirect(routes.HomeCtr.index());
                    }
                }else if(school.status.equals(Status.InActivate.getValue())){
                    //return to a payment page
                    flash().put("error","Your account have expiry please make a payment ");
                    return redirect(routes.HomeCtr.index());
                }

                /*if(OnlineStatus.Online.equals(sessionCheckPoint(school._id,user._id).onlineStatus)){
                    flash().put("error","You have already log in with a device before please try and logout before you can login ");
                    return redirect(routes.HomeCtr.loginPage());
                }*/

                session().clear();
                signInSession(log.user_email);

                System.out.println("Pass:");
                flash().put("success","Successfully Login");
                return redirect(routes.HomeCtr.dashboard());
            }else if(user.status.equals(Status.InActivate.getValue())){
                flash().put("error","Your record has been deactivated. Please contact your administrator for help.");
                return redirect(routes.HomeCtr.loginPage());
            }else if(user.status.equals(Status.Pending.getValue())){
                flash().put("info","Your are not fully register. please reverence to sending email by QUSI to complete your registration.");
                return redirect(routes.HomeCtr.index());
            }else if(user.status.equals(Status.Deleted.getValue())){
                flash().put("info","You don't longer have record with "+school.name+" ");
                return redirect(routes.HomeCtr.index());
            }

        }

        flash().put("error","The email and password details is incorrect");
        return redirect(routes.HomeCtr.loginPage());
    }
// lock screen for that period of time if use do noting on the website
    /*public Result lockScreen(){

        session().put("password")
    }
*/
    // logout clear the session
    // delete the users from the session collection
    public Result logout(){
        session().clear();
     //   gatewaySession(OnlineStatus.Offline.getValue());
        flash().put("success","Successfully Logout");
        return redirect(routes.HomeCtr.loginPage());
    }
    // login page
    public Result loginPage(){
        return ok(views.html.layout.client_page.login.render());
    }
}
