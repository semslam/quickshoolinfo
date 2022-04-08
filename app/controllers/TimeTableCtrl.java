package controllers;

import api.auth.Auth;
import data_services.SchoolServices;
import data_services.TimeTableService;
import data_services.actors_record.staff.StaffRecord;
import data_services.sch_config.SchConfigServices;
import models.education.config.SchoolConfiguration;
import models_views.StaffUsers;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu on 03/08/2017.
 */
public class TimeTableCtrl extends Controller{
    public TimeTableService timeTableService;
    public SchConfigServices schConfigServices;
    public SchoolServices schoolServices;
    public StaffRecord staffRecord;


    private boolean checkResult = false;
    @Inject
    private TimeTableCtrl(TimeTableService timeTableService,
                          SchConfigServices schConfigServices,
                          StaffRecord staffRecord,
                          SchoolServices schoolServices){
        this.timeTableService = timeTableService;
        this.schConfigServices = schConfigServices;
        this.schoolServices = schoolServices;
        this.staffRecord = staffRecord;
    }

    public Result classTimeTableForm(){
        SchoolConfiguration schConfig = new SchoolConfiguration();
        schConfig.classRooms = schConfigServices.getAllClassRoom(Auth.sessionSchoolId());
        schConfig.grades = schConfigServices.getAllGrade(Auth.sessionSchoolId());
        schConfig.periods = schConfigServices.getAllPeriod(Auth.sessionSchoolId());
        schConfig.subjects = schConfigServices.getAllSubject(Auth.sessionSchoolId());
        List<StaffUsers> staffUserses = staffRecord.findAllStaff();
        return ok(views.html.authpage.timetables.class_timetable.render(schConfig,staffUserses,schoolServices));
    }
    public Result addClassTimeTable(){
        Form<TimeTableService> timeTableServiceForm = Form.form(TimeTableService.class).bindFromRequest();
        TimeTableService tTableService = timeTableServiceForm.get();
        System.out.println(tTableService.subOfTheDays.listIterator().next().toString());
        System.out.println(tTableService.timeTable.toString());
        checkResult = timeTableService.insertSubjectTimeTable(tTableService);
        if(!checkResult){
            flash().put("error","Time Table was not save try again");
            return redirect(routes.TimeTableCtrl.addClassTimeTable());
        }
        flash().put("success","Time Table successful save");
        return redirect(routes.TimeTableCtrl.addClassTimeTable());
    }

    public Result teacherSubjectManager(){
        // Get days of the week and use the teacher id get where teacher id are equal
        // subjectOfTheDay collection with subject period class
      return  ok();
    }

    public Result editClassTimeTable(){
        return ok();
    }

    public Result deleteClassTimeTable(long id){
        return ok();
    }
    public Result previewClassTimeTable(){
        return ok();
    }


}
