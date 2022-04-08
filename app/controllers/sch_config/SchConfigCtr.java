package controllers.sch_config;

import api.auth.Auth;
import api.util.DateFormat;
import api.util.IDGenerator;
import controllers.QusiController;
import data_services.sch_config.SchConfigServices;
import models.education.config.*;
import models.education.fees.Fee;
import models.education.fees.FeeDetail;
import models.time_table.TimeTable;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.*;

import javax.inject.*;
import java.util.*;

/**
 * Created by Ibrahim Olanrewaju Semiu on 07/01/2017.
 */
public class SchConfigCtr extends QusiController {

    private SchConfigServices schoolConfigView;
    private boolean checkResult = false;

    @Inject
    public SchConfigCtr(SchConfigServices schConfigServices){
        this.schoolConfigView = schConfigServices;
    }


    // each school sub nodule should have there own representative class object

    public Result getSchConfigWorkFllow(){

        return ok(views.html.authpage.sch_config.sch_config.render(getAllTerm(),getAllPosition()
                ,getAllClassRoom(),getAllGrade()
                ,getAllDepartment(),getAllClassRoomType()
                ,getAllSubject()));
    }

    public Result insertClassRoom(){
        play.data.Form<ClassRoom> classRoomForm = play.data.Form.form(ClassRoom.class).bindFromRequest();
        if(classRoomForm.hasErrors()){
            flash().put("error","It cant be empty");
        }
        ClassRoom classRoom = classRoomForm.get();



        String btnAction = DynamicForm.form().bindFromRequest().get("action");
        if(classRoom.id <= 0){
            classRoom.id = IDGenerator.subDocId();
            classRoom.modifier = Auth.sessionUsersId();
            classRoom.modified = new Date();
            classRoom.lastModified = new Date();
            schoolConfigView.classRoom = Arrays.asList(classRoom) ;
           checkResult =  schoolConfigView.addSchConfig(schoolConfigView);
        }else {
            classRoom.modifier = Auth.sessionUsersId();
            classRoom.lastModified = new Date();
            schoolConfigView.classRoom = Arrays.asList(classRoom) ;
            checkResult = schoolConfigView.uploadSchConfig(schoolConfigView);
        }

        if(!checkResult){
            flash().put("error","Invalid input please try again");
            return  redirect(controllers.sch_config.routes.SchConfigCtr.classRoomForm());
        }
        flash().put("success","Class Room was Successful Save");
        return redirect(controllers.sch_config.routes.SchConfigCtr.listClassRooms());
    }

    public Result classRoomForm(){
        ClassRoom classRoom = new ClassRoom();
        Form<ClassRoom> form = Form.form(ClassRoom.class).fill(classRoom);
        return ok(views.html.authpage.sch_config.class_conf.add.render(form,getAllGrade(),getAllClassRoomType(),getAllDepartment()));
    }

    public Result listClassRooms(){
        return ok(views.html.authpage.sch_config.class_conf.list.render(getAllClassRoom(),getAllDepartment(),getAllClassRoomType(),getAllGrade()));
    }

    public Result insertGrade(){
        play.data.Form<Grade> gradeForm = play.data.Form.form(Grade.class).bindFromRequest();
        if(gradeForm.hasErrors()){
            flash().put("error","It cant be empty");
        }
        Grade grade = gradeForm.get();
        System.out.println("\n Grade: "+ grade.toString());
        System.out.println();

        String btnAction = DynamicForm.form().bindFromRequest().get("action");
        if(grade.id <= 0){
            grade.id = IDGenerator.subDocId();
            grade.modifier = Auth.sessionUsersId();
            grade.modified = new Date();
            grade.lastModified = new Date();

            schoolConfigView.grades = Arrays.asList(grade) ;

            // inserts sub doc
            checkResult = schoolConfigView.addSchConfig(schoolConfigView);
        }else{

            // update sub doc
            grade.modifier = Auth.sessionUsersId();
            grade.lastModified = new Date();
            schoolConfigView.grades = Arrays.asList(grade) ;
            checkResult = schoolConfigView.uploadSchConfig(schoolConfigView);
        }

        if(!checkResult){
            flash().put("error","Invalid input please try again");
            return redirect(controllers.sch_config.routes.SchConfigCtr.gradeFormAndList());
        }
        flash().put("success","Grade Successful Save");
        return redirect(controllers.sch_config.routes.SchConfigCtr.gradeFormAndList());
    }

    public Result gradeFormAndList(){
        return ok(views.html.authpage.sch_config.grade_conf.list.render(getAllGrade()));
    }

    public Result insertTerm(){
        Form<Term> termForm = Form.form(Term.class).bindFromRequest();
        if(termForm.hasErrors()){
            flash().put("error","It cant be empty");
        }
        Term term = termForm.get();

        if(term.id <= 0){
            term.id = IDGenerator.subDocId();
            term.modifier = Auth.sessionUsersId();
            term.modified = new Date();
            term.lastModified = new Date();

            schoolConfigView.term = Arrays.asList(term);
            // inserts sub doc
            checkResult = schoolConfigView.addSchConfig(schoolConfigView);
        }else{

            // update sub doc
            term.modifier = Auth.sessionUsersId();
            term.lastModified = new Date();
            schoolConfigView.term = Arrays.asList(term) ;
            checkResult = schoolConfigView.uploadSchConfig(schoolConfigView);
        }
        if(!checkResult){
            flash().put("error","Invalid input please try again");
            return redirect(controllers.sch_config.routes.SchConfigCtr.termFormAndList());
        }
        flash().put("success","Term Successfully save");
        return redirect(controllers.sch_config.routes.SchConfigCtr.termFormAndList());
    }

    public Result termFormAndList(){
        return ok(views.html.authpage.sch_config.term_conf.list.render(getAllTerm()));
    }

    public Result subjectForm(){
        return ok(views.html.authpage.sch_config.subject_conf.add.render(getAllDepartment()));
    }

    public Result insertSubject(){
        play.data.Form<Subject> subjectForm = play.data.Form.form(Subject.class).bindFromRequest();
        if(subjectForm.hasErrors()){
            flash().put("error","It cant be empty");
        }
        Subject subject = subjectForm.get();

        if(subject.id <= 0){
            subject.id = IDGenerator.subDocId();
            subject.modified = Auth.sessionUsersId();
            subject.modifierDate = new Date();
            subject.lastModified = new Date();
            schoolConfigView.subject = Arrays.asList(subject);
            System.out.println(subject.toString());
            // inserts sub doc
            checkResult = schoolConfigView.addSchConfig(schoolConfigView);
        }else{

            // update sub doc
            subject.modified = Auth.sessionUsersId();
            subject.lastModified = new Date();
            schoolConfigView.subject = Arrays.asList(subject) ;
            System.out.println(subject.toString());
            checkResult = schoolConfigView.uploadSchConfig(schoolConfigView);
        }
        if(!checkResult){
            flash().put("error","Invalid input please try again");
            return redirect(controllers.sch_config.routes.SchConfigCtr.subjectForm());
        }

        flash().put("success","Subject Successfully Save");
        return redirect(controllers.sch_config.routes.SchConfigCtr.subjectList());
    }

    public Result subjectList(){
        List<Subject> subjectList = schoolConfigView.getAllSubject(Auth.sessionSchoolId());
         if(subjectList == null || subjectList.size() == 0){
             subjectList = new ArrayList<>();
        }
        return ok(views.html.authpage.sch_config.subject_conf.list.render(subjectList,getAllDepartment()));
    }

    public Result insertPeriod(){
        play.data.Form<Period> periodForm = play.data.Form.form(Period.class).bindFromRequest();
        if(periodForm.hasErrors()){
            flash().put("error","It cant be empty");
        }

        Period period = periodForm.get();
        if(period.id <= 0){
            System.out.println(period.toString());
           /* period.startTime = DateFormat.getTimeFormat(period.startTime);
            period.endTime = DateFormat.getTimeFormat(period.endTime);*/
            period.id = IDGenerator.subDocId();
            period.modified = Auth.sessionUsersId();
            period.modifierDate = DateFormat.getFormatToDate();
            period.lastModified = DateFormat.getFormatToDate();
            schoolConfigView.periods = Arrays.asList(period);

            // inserts sub doc
            checkResult = schoolConfigView.addSchConfig(schoolConfigView);
        }else{
            // update sub doc
            period.modified = Auth.sessionUsersId();
            period.lastModified = DateFormat.getFormatToDate();
            schoolConfigView.periods = Arrays.asList(period) ;
            checkResult = schoolConfigView.uploadSchConfig(schoolConfigView);
        }

        if(!checkResult){
            flash().put("error","Invalid input please try again");
            return redirect(controllers.sch_config.routes.SchConfigCtr.periodFormAndList());
        }
        flash().put("success","Period was successful save");
        return redirect(controllers.sch_config.routes.SchConfigCtr.periodFormAndList());
    }

    public Result periodFormAndList(){
        return ok(views.html.authpage.sch_config.period_conf.list.render(getAllPeriod()));
    }

    public Result insertPosition(){
        play.data.Form<Position> positionForm = play.data.Form.form(Position.class).bindFromRequest();
        if(positionForm.hasErrors()){
            flash().put("error","It cant be empty");
        }
        Position position = positionForm.get();
        //String[] roles = request().body().asFormUrlEncoded().get("role[]");
        if(position.id <= 0){
            position.id = IDGenerator.subDocId();
            //position.roles = roles;
            position.modifier = Auth.sessionUsersId();
            position.modified = new Date();
            position.lastModified = new Date();
            System.out.println(Arrays.asList(position.roles));
            schoolConfigView.positions = Arrays.asList(position);
           // System.out.println(Arrays.toString(position.roles));
            // inserts sub doc
            checkResult = schoolConfigView.addSchConfig(schoolConfigView);
        }else{
            // update sub doc
            position.modifier = Auth.sessionUsersId();
            position.lastModified = new Date();
            schoolConfigView.positions = Arrays.asList(position) ;
            System.out.println(Arrays.asList(position.roles));
            checkResult = schoolConfigView.uploadSchConfig(schoolConfigView);

        }

        if(!checkResult){
            flash().put("error","Invalid input please try again");
            return redirect(controllers.sch_config.routes.SchConfigCtr.positionFormAndList());
        }
        flash().put("success","Position was Successfully save");
        return redirect(controllers.sch_config.routes.SchConfigCtr.positionFormAndList());
    }

    public Result positionFormAndList(){
        return ok(views.html.authpage.sch_config.position_conf.list.render(getAllPosition()));
    }

    public Result insertAttendance(){
        play.data.Form<Attendance> attendanceForm = play.data.Form.form(Attendance.class).bindFromRequest();
        if(attendanceForm.hasErrors()){
            flash().put("error","It cant be empty");
        }
        Attendance attendance = attendanceForm.get();
        schoolConfigView.attendance = Arrays.asList(attendance) ;
        boolean check = schoolConfigView.uploadSchConfig(schoolConfigView);
        if(!check){
            flash().put("error","Invalid input please try again");
        }
        flash().put("success","Successful");
        return redirect(controllers.sch_config.routes.SchConfigCtr.listClassRooms());
    }

    public Result listAttendance(){
        schoolConfigView.getAllAttendance(Auth.sessionSchoolId());
        return  TODO;
    }

    public Result listTerm(){
        schoolConfigView.getAllTerm(Auth.sessionSchoolId());
        return  TODO;
    }

    public Result insertFee(){
        Form<Fee> feeForm = Form.form(Fee.class).bindFromRequest();
        if(feeForm.hasErrors()){
            flash().put("error","It cant be empty");
            badRequest(views.html.authpage.sch_config.fee_conf.add.render());
        }
        System.out.println("Date Format: "+feeForm.get().lastModified);
        Fee fees = feeForm.get();
        Fee fee = schoolConfigView.feeProcess(fees);
        if(fee.id <= 0){
            fee.id = IDGenerator.subDocId();
            fee.modifier = Auth.sessionUsersId();
            fee.modified = new Date();
            fee.lastModified = new Date();
            schoolConfigView.fee = Arrays.asList(fee) ;
            checkResult = schoolConfigView.addSchConfig(schoolConfigView);
        }else{
            fee.modifier = Auth.sessionUsersId();
            fee.lastModified = new Date();
            schoolConfigView.fee = Arrays.asList(fee) ;
            checkResult = schoolConfigView.uploadSchConfig(schoolConfigView);
        }
        if(!checkResult){
            flash().put("error","Invalid input please try again");
            return redirect(controllers.sch_config.routes.SchConfigCtr.feeMasterList());
        }
        flash().put("success","Fee was Successful save");
        return redirect(controllers.sch_config.routes.SchConfigCtr.feeMasterList());
    }

    public Result insertFeeDetail(){
        Form<FeeDetail> feeDetailForm = Form.form(FeeDetail.class).bindFromRequest();
        if(feeDetailForm.hasErrors()){
            flash().put("error","It cant be empty");
            badRequest(views.html.authpage.sch_config.fee_conf.add.render());
        }
       // System.out.println("Date Format: "+feeDetailForm.get().lastModified);
        FeeDetail feeDetail = feeDetailForm.get();
        FeeDetail feeDetails = schoolConfigView.feeDetailProcess(feeDetail);
        if(feeDetail.id <= 0){
            feeDetails.id = IDGenerator.subDocId();
            feeDetails.modifier = Auth.sessionUsersId();
            feeDetails.modified = new Date();
            feeDetails.lastModified = new Date();
            schoolConfigView.feeDetails = Arrays.asList(feeDetails) ;
            checkResult = schoolConfigView.addSchConfig(schoolConfigView);
        }else{
            feeDetails.modifier = Auth.sessionUsersId();
            feeDetails.lastModified = new Date();
            schoolConfigView.feeDetails = Arrays.asList(feeDetails) ;
            checkResult = schoolConfigView.uploadSchConfig(schoolConfigView);
        }
        if(!checkResult){
            flash().put("error","Invalid input please try again");
            return redirect(controllers.sch_config.routes.SchConfigCtr.feeMasterList());
        }
            flash().put("success","Fee Detail was Successful save");
            return redirect(controllers.sch_config.routes.SchConfigCtr.feeMasterList());
    }

   /* public Result feeFrom(){
        return ok(views.html.authpage.sch_config.fee_conf.add.render());
    }*/

    public Result feeMasterList(){
        return ok(views.html.authpage.sch_config.fee_conf.fee_master.render(getAllFee(),getAllFeeDetail(),getAllGrade()));
    }

    public Result insertTimeTable(){
        Form<TimeTable> timeTableForm = play.data.Form.form(TimeTable.class).bindFromRequest();
        if(timeTableForm.hasErrors()){
            flash().put("error","It cant be empty");
        }
        TimeTable timeTable = timeTableForm.get();
        schoolConfigView.timeTable = Arrays.asList(timeTable) ;
        boolean check = schoolConfigView.uploadSchConfig(schoolConfigView);
        if(!check){
            flash().put("error","Invalid input please try again");
        }
        flash().put("success","Successful");
        return redirect(controllers.sch_config.routes.SchConfigCtr.listClassRooms());
    }

    public Result listTimeTable(){
        schoolConfigView.getAllTimeTable(Auth.sessionSchoolId());
        return  TODO;
    }

    public Result insertDepartment(){
        Form<Department> departmentForm = play.data.Form.form(Department.class).bindFromRequest();
        if(departmentForm.hasErrors()){
            flash().put("error","It cant be empty");
            return redirect(controllers.sch_config.routes.SchConfigCtr.listDepartment());
        }
        Department department = departmentForm.get();
        if(department._id <= 0){
            department._id = IDGenerator.subDocId();
            department.modifier = Auth.sessionUsersId();
            department.modified = new Date();
            department.lastModified = new Date();
            schoolConfigView.departments = Arrays.asList(department);
            checkResult = schoolConfigView.addSchConfig(schoolConfigView);
        }else{
            department.modifier = Auth.sessionUsersId();
            department.lastModified = new Date();
            schoolConfigView.departments = Arrays.asList(department) ;
            checkResult = schoolConfigView.uploadSchConfig(schoolConfigView);
        }

        if(!checkResult){
            flash().put("error","Invalid input please try again");
            return redirect(controllers.sch_config.routes.SchConfigCtr.listDepartment());
        }
        flash().put("success","Successful save");
        return redirect(controllers.sch_config.routes.SchConfigCtr.listDepartment());
    }

    public Result listDepartment(){
        return ok(views.html.authpage.sch_config.department_conf.add_and_list.render(getAllDepartment()));
    }

    public Result insertClassRoomtype(){
        play.data.Form<ClassRoomType> classRoomTypeForm = play.data.Form.form(ClassRoomType.class).bindFromRequest();
        if(classRoomTypeForm.hasErrors()){
            flash().put("error","It cant be empty");
            return redirect(controllers.sch_config.routes.SchConfigCtr.listClassRoomtype());
        }
        ClassRoomType classRoomType = classRoomTypeForm.get();
        if(classRoomType.id <= 0){
            classRoomType.id = IDGenerator.subDocId();
            classRoomType.modifier = Auth.sessionUsersId();
            classRoomType.modified = new Date();
            classRoomType.lastModified = new Date();
            schoolConfigView.classRoomTypes = Arrays.asList(classRoomType) ;
            checkResult = schoolConfigView.addSchConfig(schoolConfigView);
        }else{
            classRoomType.modifier = Auth.sessionUsersId();
            classRoomType.lastModified = new Date();
            schoolConfigView.classRoomTypes = Arrays.asList(classRoomType) ;
            checkResult = schoolConfigView.uploadSchConfig(schoolConfigView);
        }
        if(!checkResult){
            flash().put("error","Invalid input please try again");
        }
        flash().put("success","Successful save");
        return redirect(controllers.sch_config.routes.SchConfigCtr.listClassRoomtype());
    }

    public Result listClassRoomtype(){
        schoolConfigView.getAllClassRoomType(Auth.sessionSchoolId());
        return ok(views.html.authpage.sch_config.classtype_conf.add_and_list.render(getAllClassRoomType()));
    }

    public List<Term> getAllTerm(){
        return schoolConfigView.getAllTerm(Auth.sessionSchoolId());
    }

    public List<Position> getAllPosition(){
       return schoolConfigView.getAllPosition(Auth.sessionSchoolId());
    }

    public List<Period> getAllPeriod(){
        return schoolConfigView.getAllPeriod(Auth.sessionSchoolId());
    }

    public List<ClassRoom> getAllClassRoom(){
      return schoolConfigView.getAllClassRoom(Auth.sessionSchoolId());
    }

    public List<Fee> getAllFee(){
        return schoolConfigView.getAllFee(Auth.sessionSchoolId());
    }

    public List<FeeDetail> getAllFeeDetail(){
        return schoolConfigView.getAllFeeDetail(Auth.sessionSchoolId());
    }

    public List<Grade> getAllGrade(){
        return schoolConfigView.getAllGrade(Auth.sessionSchoolId());
    }

    public List<Department> getAllDepartment(){
        return schoolConfigView.getAllDepartment(Auth.sessionSchoolId());
    }

    public List<ClassRoomType> getAllClassRoomType(){
        return schoolConfigView.getAllClassRoomType(Auth.sessionSchoolId());
    }

    public List<Subject> getAllSubject(){
        return schoolConfigView.getAllSubject(Auth.sessionSchoolId());
    }

    public List<Attendance> getAllAttendance(){
        return schoolConfigView.getAllAttendance(Auth.sessionSchoolId());
    }

    public Result deleteClassRooms(long id){
        return TODO;
    }

    public Result deleteAttendance(long id){return TODO;}

    public Result deleteTerm(long id){
        return TODO;
    }

    public Result deleteFee(long id){return TODO;}

    public Result deleteTimeTable(long id){return TODO;}
}
