package controllers;

import api.auth.Auth;
import dao_implimentation.SchoolDaoQuery;
import data_services.SchoolServices;
import models.education.School;
import play.data.Form;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.Date;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu on 07/08/2017.
 */
public class SchoolCtrl extends QusiController{

   private SchoolServices schoolServices;
   private School school;
    private boolean checkResult = false;
    @Inject
    public SchoolCtrl(SchoolServices schoolServices){
      this.schoolServices = schoolServices;
      this.school = new School();
    }

    public Result schoolForm(){
        School school = schoolServices.find();
        return ok(views.html.authpage.sch.edit.render(school));
    }

    public Result updateSchool(){
        Form<School> schoolForm = Form.form(School.class).bindFromRequest();
        if(schoolForm.hasErrors()){
            flash().put("error","Invalid input field");
            return redirect(routes.SchoolCtrl.schoolForm());
        }

        School school = schoolForm.get();
        school._id = Auth.sessionSchoolId();
        school.modifier = Auth.sessionUsersId();
        school.modifierDate = new Date();
        checkResult = schoolServices.update(school);
        if(!checkResult){
            flash().put("error","Invalid input please try again");
            return  redirect(routes.SchoolCtrl.schoolForm());
        }

        flash().put("success","School successfully save ");
        return redirect(routes.HomeCtr.pageDashboard());
    }
}
