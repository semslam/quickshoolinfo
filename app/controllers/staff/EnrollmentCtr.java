package controllers.staff;

import data_services.actors_record.staff.StaffRecord;
import models.Users;
import models.staff_records.Staff;
import models_views.StaffUsers;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju Semiu on 25/05/2017.
 */
public class EnrollmentCtr extends Controller {

    private StaffRecord staffRecord;
    private Staff staff;
    private Users users;
    private  boolean checkResult;

    @Inject
    private EnrollmentCtr(StaffRecord staffRecord){
        this.staff = new Staff();
        this.users = new Users();
        this.staffRecord = staffRecord;
    }

    public Result getForm(){
        return ok(views.html.authpage.staff.add.render());
    }

    public Result addNewStaff(){
        Form<Staff> staffForm =  Form.form(Staff.class).bindFromRequest();
        if (staffForm.hasErrors()){
            flash().put("error","invalid input error");
            badRequest(views.html.authpage.staff.add.render());
        }
        //users.id = Long.parseLong(staffForm.bindFromRequest().data().get("id"));
        users.role = staffForm.bindFromRequest().data().get("role");
        users.designation = staffForm.bindFromRequest().data().get("position");
        users.userEmail = staffForm.bindFromRequest().data().get("userEmail");

        staff = staffForm.get();
        //if(staff.id <=0){
            checkResult = staffRecord.insertStaff(staff,users);
        //}else{

       // }
        //Form<StaffRecord> form = Form.form(StaffRecord.class).fill(staffR);
        if(!checkResult){
            flash().put("error","Staff was not save try again");
            return badRequest(views.html.authpage.staff.add.render());
        }
        flash().put("success","Staff Info successfully save");
        return redirect(controllers.staff.routes.EnrollmentCtr.findAllStaff());
    }


    public Result biographyUpdate(){
        Form<Staff> staffForm =  Form.form(Staff.class).bindFromRequest();
        if (staffForm.hasErrors()){
            flash().put("error","invalid input error");
            badRequest(views.html.authpage.staff.add.render());
        }
        staff = staffForm.get();

        checkResult = staffRecord.updateBiography(staff);
        if(!checkResult){
            flash().put("error","Staff was not save try again");
            return redirect(controllers.staff.routes.EnrollmentCtr.findAllStaff());
        }
        flash().put("success","Staff Info successfully save");
        return redirect(controllers.staff.routes.EnrollmentCtr.findAllStaff());
    }

    public Result getStaffPreview(long id){
        StaffRecord staffRecords  = new StaffRecord();
        if(id != 0){
            staffRecords = staffRecord.findStaffById(id);
        }
        if(staffRecords == null){
            flash().put("error"," No pointer Exception");
            return redirect(controllers.staff.routes.EnrollmentCtr.findAllStaff());
        }
        return ok(views.html.authpage.staff.view.render(staffRecords));
    }

    public Result findAllStaff(){
        List<StaffUsers> staffUsers = staffRecord.findAllStaff();
        return ok(views.html.authpage.staff.list.render(staffUsers));
    }

    public Result updateStaff(){
        return  TODO;
    }

    public Result deleteStaff(long id){
        if(!(id <=0)){

            checkResult = staffRecord.subDeleteStaffById(id);
        }
        if(!checkResult){
            flash().put("error","Staff was not delete try again");
            return redirect(controllers.staff.routes.EnrollmentCtr.findAllStaff());
        }
        flash().put("success","Staff  successfully delete");
        return redirect(controllers.staff.routes.EnrollmentCtr.findAllStaff());
    }


}
