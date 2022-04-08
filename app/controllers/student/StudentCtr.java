package controllers.student;

import controllers.student.*;
import data_services.actors_record.student.StudentRecord;
import models.std_records.Student;
import models_views.StudentUsers;
import play.data.Form;
import play.mvc.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju S on 16/10/2016.
 */
public class StudentCtr extends Controller {

    private StudentRecord studentRecord;
    private boolean checkResult;

    @Inject
    private StudentCtr(StudentRecord studentRecord){
        this.studentRecord = studentRecord;
    }




    public Result updateStudent(){
        return  TODO;
    }

    public Result updateStdentInfo(){
        return ok();
    }

    public Result updateStdHealth(){
        return ok();
    }

    // do not belong to this class
    public Result updateStdGuardian(){
        return ok();
    }

}
