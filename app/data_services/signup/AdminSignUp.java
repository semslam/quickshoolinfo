package data_services.signup;

import api.util.IDGenerator;
import dao_implimentation.SchoolDaoQuery;
import dao_implimentation.StaffDaoQuery;
import dao_implimentation.UserDaoQuery;
import models.enum_config.Roles;
import models.enum_config.Status;
import models.staff_records.Staff;
import models.Users;
import models.education.School;

import javax.inject.Inject;
import java.util.Date;

/**
 * Created by Ibrahim Olanrewaju S on 09/08/2016.
 */

public class AdminSignUp {

    private UserDaoQuery userDaoQuery;
    private SchoolDaoQuery schoolDaoQuery;
    private StaffDaoQuery staffDaoQuery;
    private boolean checkResult = false;
    
    public String subDomain;
    public String email;
    public String teamsAndConditions;

    @Inject
    private AdminSignUp(SchoolDaoQuery schoolDaoQuery,
                       UserDaoQuery userDaoQuery,
                       StaffDaoQuery staffDaoQuery){
        this.schoolDaoQuery = schoolDaoQuery;
        this.userDaoQuery = userDaoQuery;
        this.staffDaoQuery = staffDaoQuery;
    }
    public AdminSignUp(){}

 public void sendEmail(String mailQuery, String tokenQuery){
     //Mailer mailer = new Mailer();
//     Mailer.sendMail(new Mailer.Envelop("QUSI SignUP Confirmation Code", views.html.emails.signup_confirmation_email.render(mailQuery,tokenQuery).body(), mailQuery));
 }
    public Users userInfo(String query){return userDaoQuery.find(query);}

    /*public String isUserActive(AdminSignUp adminSignUp){
        String check = "Empty";
        try {
            System.out.println();
            System.out.println(adminSignUp.email +" : "+ adminSignUp.subDomain );

            Users users = userDaoQuery.find("{ user_email: { $exists: true, $nin: [ '"+ adminSignUp.email+"' ] } }");

            School school = schoolDaoQuery.find("{ website: { $exists: true, $nin: [ '"+ adminSignUp.subDomain+"' ] } }");
            System.out.println();
            System.out.println(adminSignUp.email +" : "+ adminSignUp.subDomain +" "+ users.userEmail+"  "+school.subDomain);

        if(users.userEmail == null || school.subDomain == null){
            if(users.userEmail != adminSignUp.email && school.subDomain != adminSignUp.subDomain){
                check = "new";
            }

        }else if((adminSignUp.email.equals(users.userEmail) || adminSignUp.subDomain.equals(school.subDomain) )){
                check = "exist";
                if(((users.status.equals(Status.Activate.getValue())) && (users.status.equals(Status.Pending.getValue())))){
                    check = "activate";
                }else{
                    check = "not-activate";
                }
        }
        }catch (Exception e){
            System.out.println("No pointer Exception: ");
            e.getMessage();
        }
            return check;
    }*/

    public Users signUpEmailVerify(AdminSignUp adminSignUp){
        Users users = userDaoQuery.find("{userEmail:'"+adminSignUp.email+"'}");
        if(users != null){
            return  users;
        }
        return null;
    }
    public School signUpSubDomainVerify(AdminSignUp adminSignUp){
        School school = schoolDaoQuery.find("{subDomain:'"+ adminSignUp.subDomain+"'}");
        if(school != null){
            return school;
        }
        return null;
    }

   /* public boolean checkSchoolName(String field, String query){
        return userDaoQuery.isSchoolNameExist(field,query);
    }*/

    public boolean userInsert(AdminSignUp adminSignUp){
        Users users = new Users();
        School school = new School();
        Staff staff  = new Staff();
        if(adminSignUp != null){
            Long genericId = IDGenerator.userIdGenerator();
            String schoolId = IDGenerator.stringUUID();
            String tokenCode = IDGenerator.tokenCode();

            users.modifier = genericId;
            users.modifierDate = new Date();
            users.lastModified = new Date();
            users._id = genericId;
            users.schoolId = schoolId;
            users.schoolBranch = "Heard Of Office";
            users.userEmail = adminSignUp.email;
            users.role = Roles.SUPPER_ADMIN.getValue();
            users.status = Status.Pending.getValue();
            users.tokenCode = tokenCode;

            school.modifier = genericId;
            school.modifierDate = new Date();
            school.lastModified = new Date();
            school._id = schoolId;
            school.status = Status.Pending.getValue();
            school.subDomain = adminSignUp.subDomain;
            school.modifier = genericId;
            school.teamsAndConditions = adminSignUp.teamsAndConditions;

            staff._id = genericId;
            staff.schoolId = schoolId;
            staff.title = "";
            staff.first_name = "";
            staff.middle_name = "";
            staff.last_name = "";
            staff.gender = "";
            staff.dob = new Date();
            staff.modifier = genericId;
            staff.lastModified = new Date();
            staff.modifierDate = new Date();

            userDaoQuery.insert(users);
            schoolDaoQuery.insert(school);
            staffDaoQuery.insert(staff);

            // sending email to the client
            sendEmail(users.userEmail,users.tokenCode);

            checkResult = true;
        }

        return checkResult;
    }

    public Users findUsersInfo(String query){
        Users users = new Users();
        users = userDaoQuery.find(query);
        return users;
    }

    public boolean findTokenCode(String token){
        Users users = new Users();

        users = userDaoQuery.find("{tokenCode: '"+token+"'}");
        if(users != null && users.tokenCode.equals(token)){
            checkResult = true;
        }
        return checkResult;
    }


}
