package controllers.student;

/*
 * File   : AdmissionCtr.java
 * Date   : 2016
 * Version: 1.00
 * Author : Ibrahim Olanrewaju Semiu
 * Modified Date : feb 17 2016
 * Copyright (c) 2016
 */

import data_services.actors_record.student.StudentRecord;
import data_services.sch_config.SchConfigServices;
import models.Users;
import models_views.StudentUsers;
import play.data.Form;
import play.mvc.*;

import javax.inject.*;
import java.util.List;

public class AdmissionCtr extends Controller {

    private StudentRecord stdRrd;
    private Users users;
    private SchConfigServices schoolConfigView;
    private  boolean checkResult;

    @Inject
    public AdmissionCtr(StudentRecord stdRrd , SchConfigServices schConfigServices){
        this.schoolConfigView = schConfigServices;
        this.stdRrd = stdRrd;
        users = new Users();
    }


    public Result studentForm(){

        return ok(views.html.authpage.student.add.render(schoolConfigView));
    }
    // student record
   public Result insertStudent(){
        Form<StudentRecord> studentRecordForm = Form.form(StudentRecord.class).bindFromRequest();
        if(studentRecordForm.hasErrors()){
            flash().put("error","The input is not valued");
            badRequest(views.html.authpage.student.add.render(schoolConfigView));
        }
        StudentRecord studentRecord = studentRecordForm.get();
       if(studentRecord.students.id <= 0){
           // insert student info
           checkResult = stdRrd.insertStudent(studentRecord);
       }else {
           // update student info
           checkResult = stdRrd.updateStudent(studentRecord);
       }
        if(!checkResult){
            flash().put("error","Student was not save try again");
            badRequest(views.html.authpage.student.add.render(schoolConfigView));
        }
       flash().put("success","Staff Info successfully save");
        return redirect(controllers.student.routes.AdmissionCtr.findAllStudent());
    }

    public Result findAllStudent(){
        List<StudentUsers> studentUsers = stdRrd.findAllStudent();
        return ok(views.html.authpage.student.list.render(studentUsers));

    }

    public Result getStudentById(long id){
        StudentRecord studentRecord = new StudentRecord();
        if(id !=0){
            studentRecord = stdRrd.findStudentById(id);
        }
        return ok(views.html.authpage.student.view.render(studentRecord));
    }

    public Result previewStudent( long id){
        StudentRecord studentRecord = new StudentRecord();
        if(id !=0){
            studentRecord = stdRrd.findStudentById(id);
        }
        return ok(views.html.authpage.student.edit_std_info.render(studentRecord));
    }

    public Result deleteStudent(long id){

        if(!(id <=0)){
            checkResult = stdRrd.subDeleteStudentById(id);
        }
        if(!checkResult){
            flash().put("error","Student was not delete try again");
            return redirect(controllers.student.routes.AdmissionCtr.findAllStudent());
        }
        flash().put("success","Student successfully delete");
        return redirect(controllers.student.routes.AdmissionCtr.findAllStudent());
    }
    // Staff record


}