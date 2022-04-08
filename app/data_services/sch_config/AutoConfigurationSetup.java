package data_services.sch_config;

import models.enum_config.AutoConfigAuthenticate;
import models.enum_config.EduGrades;
import models.education.config.*;
import models.education.fees.Fee;
import models.education.fees.FeeDetail;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu on 02/04/2017.
 */
public class AutoConfigurationSetup {
    private static SchConfigServices schConfigServices;

    @Inject
    public  AutoConfigurationSetup(SchConfigServices schConfigServices){
        this.schConfigServices = schConfigServices;
    }

    // Using this class for auto school configuration and school setup


    public static List<Term> getAllTerm(){
        return schConfigServices.getAllTerm(AutoConfigAuthenticate.SCHOOL_CONFIG_ID.getValue());
    }

    public static List<Position> getAllPosition(){
        return schConfigServices.getAllPosition(AutoConfigAuthenticate.SCHOOL_CONFIG_ID.getValue());
    }

    public static List<Period> getAllPeriod(){
        return schConfigServices.getAllPeriod(AutoConfigAuthenticate.SCHOOL_CONFIG_ID.getValue());
    }

    public static List<ClassRoom> getAllClassRoom(){
        return schConfigServices.getAllClassRoom(AutoConfigAuthenticate.SCHOOL_CONFIG_ID.getValue());
    }

    public static List<Fee> getAllFee(){
        return schConfigServices.getAllFee(AutoConfigAuthenticate.SCHOOL_CONFIG_ID.getValue());
    }

    public static List<FeeDetail> getAllFeeDetail(){
        return schConfigServices.getAllFeeDetail(AutoConfigAuthenticate.SCHOOL_CONFIG_ID.getValue());
    }

    public static List<Department> getAllDepartment(){
        return schConfigServices.getAllDepartment(AutoConfigAuthenticate.SCHOOL_CONFIG_ID.getValue());
    }

    public static List<ClassRoomType> getAllClassRoomType(){
        return schConfigServices.getAllClassRoomType(AutoConfigAuthenticate.SCHOOL_CONFIG_ID.getValue());
    }

    public static List<Subject> getAllSubject(){
        return schConfigServices.getAllSubject(AutoConfigAuthenticate.SCHOOL_CONFIG_ID.getValue());
    }

    public static List<Attendance> getAllAttendance(){
        return schConfigServices.getAllAttendance(AutoConfigAuthenticate.SCHOOL_CONFIG_ID.getValue());
    }

    public static List<Grade> getAllGrade(String value){
        if(!value.isEmpty()){
            if(value.equals(EduGrades.KJ_PRIMARY.getValue())){
                return  schConfigServices.getAllGrade(AutoConfigAuthenticate.SCHOOL_CONFIG_ID.getValue()).subList(0,8);
            }else if(value.equals(EduGrades.HIGH_SCHOOL.getValue())){
                return  schConfigServices.getAllGrade(AutoConfigAuthenticate.SCHOOL_CONFIG_ID.getValue());
            }
        }
        return  new ArrayList<>();
    }

    /*public List<Term> addTermList(){
        List<Term> termList = new ArrayList<>();
        termList.add(new Term(IDGenerator.subDocId(),"",new Date(),new Date(),11));
    }*/
}
