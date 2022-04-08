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


public class StaffCtr extends Controller {

    private StaffRecord staffRecord;
    private Staff staff;
    private Users user;
    private  boolean checkResult;

    @Inject
    private StaffCtr(StaffRecord staffRecord){
        this.staff = new Staff();
        this.staffRecord = staffRecord;
    }

    public Result getStaffById(long id){
    StaffRecord staffRecords  = new StaffRecord();
        if(id != 0){
            staffRecords   = staffRecord.findStaffById(id);
        }else{
            // return back to preview
        }
        Form<StaffRecord> form = Form.form(StaffRecord.class).fill(staffRecords);
        return ok(views.html.authpage.staff.edit_staff_info.render(form));
    }

    public Result getStaffBiography(long id){
        StaffRecord staffRecords = new StaffRecord();
        if(id != 0){
            staffRecords   = staffRecord.findStaffById(id);
        }
        Form<StaffRecord> form = Form.form(StaffRecord.class).fill(staffRecords);
        return ok(views.html.authpage.staff.edit_staff_biography.render(form));
    }

    public Result getStaffContact(long id){
        Staff staff = new Staff();
        if(id != 0){
            staffRecord.findStaffById(id);
        }
        Form<Staff> form = Form.form(Staff.class).fill(staff);
        return ok(views.html.authpage.staff.contact.render());
        // return ok();
    }

    public Result updateStaffProfession(){
        return ok();
    }

    public Result updateStaffInfo(){

        Form<Staff> staffForm =  Form.form(Staff.class).bindFromRequest();
        if (staffForm.hasErrors()){
            flash().put("error","invalid input error");
            //badRequest(views.html.authpage.staff.add.render(staffInfo));
            badRequest();
        }

        Staff staff = staffForm.get();
        //Form<StaffRecord> form = Form.form(StaffRecord.class).fill(staffR);
        checkResult = staffRecord.uploadStaffInfo(staff);
        if(!checkResult){
            flash().put("error","Staff Info was not save try again");
            // badRequest(views.html.authpage.staff.add.render(staffInfo));
            badRequest();
        }

        flash().put("success","Staff Info enrollment was successfully save");
        return redirect(controllers.staff.routes.EnrollmentCtr.getStaffPreview(staff._id));
    }
}