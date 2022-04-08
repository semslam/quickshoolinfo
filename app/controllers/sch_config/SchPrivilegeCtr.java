package controllers.sch_config;

import data_services.sch_config.SchPrivilegeServices;
import models.privileges.SchPrivilege;
import play.data.Form;
import play.mvc.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju S DEV on 17/04/2017.
 */
public class SchPrivilegeCtr extends Controller {
    private SchPrivilegeServices schPrivilegeServices;
    private boolean checkResult;

    @Inject
    public SchPrivilegeCtr(SchPrivilegeServices schPrivilegeServices){
        this.schPrivilegeServices = schPrivilegeServices;
    }

    public Result updateSchPrivilege(){
        Form<SchPrivilege> schPrivilegeForm = Form.form(SchPrivilege.class).bindFromRequest();
        if(schPrivilegeForm.hasErrors()){
            flash().put("error","It cant be empty");
        }
        SchPrivilege schPrivilege = schPrivilegeForm.get();
        checkResult = schPrivilegeServices.updateSchPrivilege(schPrivilege);

        if(!checkResult){
            flash().put("error","Updating Error Please try again");
            redirect(controllers.sch_config.routes.SchPrivilegeCtr.findAllSchPrivileges());
        }

        flash().put("success","Updating was successfully");
        return redirect(controllers.sch_config.routes.SchPrivilegeCtr.findAllSchPrivileges());

    }

    public Result findAllSchPrivileges(){
        List<SchPrivilege> schPrivileges = schPrivilegeServices.findAllSchPrivilege();
        return ok(views.html.authpage.privilege.schools.list.render(schPrivileges));
    }
}
