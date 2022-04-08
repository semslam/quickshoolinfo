package controllers.privilege;

import data_services.sch_config.SchPrivilegeServices;
import models.Users;
import models.education.School;
import play.data.Form;
import play.mvc.Result;

import javax.inject.Inject;

import static play.mvc.Http.Context.Implicit.flash;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

/**
 * Created by Ibrahim Olanrewaju S on 16/04/2017.
 */

public class ClientsPrivilegeCtr {
    public SchPrivilegeServices schPrivilegeServices;
    public School school;

    @Inject
    public ClientsPrivilegeCtr(SchPrivilegeServices schPrivilegeServices){
        this.schPrivilegeServices = schPrivilegeServices;
        this.school = new School();
    }

    public Result updateClientPrivilege(){
        Form<School> schoolForm = Form.form(School.class).bindFromRequest();

        if(schoolForm.hasErrors()){
            flash().put("error","Invalid input field");
            return badRequest(views.html.authpage.privilege.clients.edit.render());
        }
        return redirect(""/*controllers.privilege.routes.ClientsPrivilegeCtr.findAllClientPrivilege()*/);
    }

    public Result findClientPrivilege(String id){
        school = schPrivilegeServices.findClientPrivilege(id);
        return ok(views.html.authpage.privilege.clients.edit.render());
    }

   public Result findSingleClientPrivilege(String id){
       school = schPrivilegeServices.findClientPrivilege(id);
       return ok(views.html.authpage.privilege.clients.edit.render());
   }

    public Result findAllClientPrivilege(){
        return ok();
    }
}
