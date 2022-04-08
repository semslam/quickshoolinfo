package controllers.guardian;

import controllers.guardian.*;
import controllers.guardian.routes;
import data_services.actors_record.guardian.GuardianRecord;
import models.Users;
import models.guardian_record.Guardian;
import models_views.GuardianUsers;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju Semiu on 29/05/2017.
 */
public class RegistrationCtr extends Controller {

    private Users users;
    private Guardian guardian;
    private GuardianUsers guardianUsers;
    private GuardianRecord guardianRecord;
    private  boolean checkResult;

    @Inject
    private RegistrationCtr(GuardianRecord guardianRecord){
        this.guardianRecord = guardianRecord;
        this.guardianUsers  = new GuardianUsers();
        this.users = new Users();
        this.guardian = new Guardian();
    }

    public Result guardianForm(){
        return ok(views.html.authpage.guardian.add.render());
    }

    public Result insertGuardian(){

        Form<Guardian> guardianForm =  Form.form(Guardian.class).bindFromRequest();
        if (guardianForm.hasErrors()){
            flash().put("error","invalid input error");
            badRequest(views.html.authpage.guardian.add.render());
        }

         guardian = guardianForm.get();
        users.userEmail = guardianForm.bindFromRequest().data().get("userEmail");

        checkResult = guardianRecord.insertNewGuardian(guardian,users);
        if(!checkResult){
            flash().put("error","Guardian was not save try again");
            return badRequest(views.html.authpage.guardian.add.render());
        }
            flash().put("success","Guardian Info successfully save");
        return redirect(controllers.guardian.routes.RegistrationCtr.findAllGuardian());
    }

    public Result getGuardianById(long id){
        GuardianRecord guardianRecords  = new GuardianRecord();
        if(id != 0){
            guardianRecords = guardianRecord.findGuardianById(id);
        }
        if(guardianRecords == null){
            flash().put("error"," No pointer Exception");
            return redirect(controllers.guardian.routes.RegistrationCtr.findAllGuardian());
        }
        return ok(views.html.authpage.guardian.edit.render(guardianRecords));
    }

    public Result getPreview(long id){
        GuardianRecord guardianRecords  = new GuardianRecord();
        if(id != 0){
            guardianRecords = guardianRecord.findGuardianById(id);
        }
        if(guardianRecords == null){
            flash().put("error"," No pointer Exception");
            return redirect(controllers.guardian.routes.RegistrationCtr.findAllGuardian());
        }
        return ok(views.html.authpage.guardian.view.render(guardianRecords));
    }

    public Result findAllGuardian(){
        List<GuardianUsers> guardianUserses = guardianRecord.findAllGuardian();
        return ok(views.html.authpage.guardian.list.render(guardianUserses));
    }

    public Result updateGuardian(){
        return  TODO;
    }

    public Result deleteGuardian(long id){
        if(id != 0){
            checkResult = guardianRecord.subDeleteGuardianById(id);
        }
        if(!checkResult){
            flash().put("error"," Guardian wasn't delete try again");
            return redirect(controllers.guardian.routes.RegistrationCtr.findAllGuardian());
        }
        flash().put("success"," Guardian Successful Delete");
        return redirect(controllers.guardian.routes.RegistrationCtr.findAllGuardian());
    }
}
