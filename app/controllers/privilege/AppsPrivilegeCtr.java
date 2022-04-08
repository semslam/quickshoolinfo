package controllers.privilege;

import data_services.AppsPrivilegeServices;
import models.privileges.ApplicationPrivilege;
import play.data.Form;
import play.mvc.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju S on 16/04/2017.
 */
public class AppsPrivilegeCtr extends Controller{

    private AppsPrivilegeServices appServices;
    @Inject
    public AppsPrivilegeCtr(AppsPrivilegeServices appServices){
        this.appServices = appServices;
    }

    public Result insertAppPrivilege(){
        Form<ApplicationPrivilege> appPrivilegeForm = Form.form(ApplicationPrivilege.class).bindFromRequest();


       // Form<ApplicationPrivilege> form = Form.form(ApplicationPrivilege.class).fill(appsPrivilege);

        if(appPrivilegeForm.hasErrors()){
            flash().put("error","Invalid input field");
            return badRequest(views.html.authpage.privilege.apps.add.render(appPrivilegeForm));
        }

        ApplicationPrivilege appsPrivilege = appPrivilegeForm.get();
        boolean result;


        if (appsPrivilege.id != 0){

            result = appServices.insertAppsPrivilege(appsPrivilege);
        }else{
            result = appServices.updateAppsPrivilege(appsPrivilege);
        }

        if(!result){
            flash().put("error","Invalid input field");
            return badRequest(views.html.authpage.privilege.apps.add.render(appPrivilegeForm));
        }
        flash().put("success","Application ClientsPrivilege insertion is successful");
        return redirect(controllers.privilege.routes.AppsPrivilegeCtr.findAllAppPrivilege());
    }

    public Result findAppPrivilegeById(long id){
        ApplicationPrivilege appPrivilege = new ApplicationPrivilege();
        if(id != 0){
            appPrivilege = appServices.findAppsPrivilege(id);
        }
        Form<ApplicationPrivilege> form = Form.form(ApplicationPrivilege.class).fill(appPrivilege);
        return ok(views.html.authpage.privilege.apps.add.render(form));
    }

    public Result findAllAppPrivilege(){
        List<ApplicationPrivilege> appPrivilegeList = appServices.findAllAppsPrivilege();
        return ok(views.html.authpage.privilege.apps.list.render(appPrivilegeList));
    }

    public Result deleteAppPrivilege(long id){
        boolean result = true;
        if(id !=0){
            result = appServices.deleteAppsPrivilege(id);
        }
        if (!result){
            flash().put("error","Application ClientsPrivilege Can't Delete From the fee_master");
            return redirect(controllers.privilege.routes.AppsPrivilegeCtr.findAllAppPrivilege());
        }
        flash().put("success","The Application ClientsPrivilege delete successfully");
        return redirect(controllers.privilege.routes.AppsPrivilegeCtr.findAllAppPrivilege());
    }
}
