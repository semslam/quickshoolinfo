package controllers.privilege;

import data_services.UserPrivilegeServices;
import models.Users;
import models.privileges.UserPrivilege;
import play.data.Form;
import play.mvc.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju S on 17/04/2017.
 */
public class UsersPrivilegeCtr extends Controller {
    private UserPrivilegeServices userPrivilegeServices;
    private boolean checkResult;

    @Inject
    public UsersPrivilegeCtr(UserPrivilegeServices userPrivilegeServices){
        this.userPrivilegeServices = userPrivilegeServices;
    }

    public Result updateSchPrivilege(){

        Form<Users> userPrivilegeForm = Form.form(Users.class).bindFromRequest();
        if(userPrivilegeForm.hasErrors()){
            flash().put("error","It cant be empty");
        }
        Users userPrivilege = userPrivilegeForm.get();
        checkResult = userPrivilegeServices.updateUserPrivilege(userPrivilege);

        if(!checkResult){
            flash().put("error","Updating Error Please try again");
            redirect(controllers.privilege.routes.UsersPrivilegeCtr.findAllSchPrivileges());
        }

        flash().put("success","Updating was successfully");
        return redirect(controllers.privilege.routes.UsersPrivilegeCtr.findAllSchPrivileges());

    }

    public Result findAllSchPrivileges(){
        Users userPrivileges = userPrivilegeServices.findAllStaffPrivilege();
        return ok(views.html.authpage.privilege.users.list.render(userPrivileges));
    }
}
