package data_services;

import api.auth.Auth;
import api.util.IDGenerator;
import dao_implimentation.time_tables.SubjOfThePeriodDetailDaoQuery;
import dao_implimentation.time_tables.SubjectOfTheDayDaoQuery;
import dao_implimentation.time_tables.TeacherSubManagDaoQuery;
import dao_implimentation.time_tables.TimeTableDaoQuery;
import models.time_table.SubjectOfTheDay;
import models.time_table.SubjectPeriod;
import models.time_table.TimeTable;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu on 03/08/2017.
 */
public class TimeTableService {
    public SubjectOfTheDayDaoQuery subOfTheDayDaoQuery;
    public SubjOfThePeriodDetailDaoQuery subOfDPeriodDetailDaoQuery;
    public TeacherSubManagDaoQuery teachSubManagDaoQuery;
    public TimeTableDaoQuery timeTableDaoQuery;
    public  boolean checkResult;

    @Inject
    private TimeTableService(SubjectOfTheDayDaoQuery subOfTheDayDaoQuery,
                             SubjOfThePeriodDetailDaoQuery subOfDPeriodDetailDaoQuery,
                             TeacherSubManagDaoQuery teachSubManagDaoQuery,
                             TimeTableDaoQuery timeTableDaoQuery){
        this.subOfTheDayDaoQuery = subOfTheDayDaoQuery;
        this.subOfDPeriodDetailDaoQuery = subOfDPeriodDetailDaoQuery;
        this.teachSubManagDaoQuery = teachSubManagDaoQuery;
        this.timeTableDaoQuery = timeTableDaoQuery;
    }

    public TimeTableService(){}

    public TimeTable timeTable;
    public List<SubjectOfTheDay> subOfTheDays;


    public boolean insertSubjectTimeTable(TimeTableService tTableService){
        // for edit check if the Objects contain id value if yes to update
        // if no, implement the one bellow
        boolean timeTableCheck, subOfTheDayCheck = false;

        List<Long> sujIds = new ArrayList<>();
        for (SubjectOfTheDay subOfDay: tTableService.subOfTheDays){
            SubjectOfTheDay subDay;
            subDay = subOfDay;
            subDay.id = IDGenerator.subDocId();
            subDay.academicYear = tTableService.timeTable.academicYear;
            subDay.schoolId = Auth.sessionSchoolId();
            subDay.subjectPeriods = subPeriodProcess(subOfDay.subjectPeriods);
            subDay.modified = Auth.sessionUsersId();
            subDay.lastModified = new Date();
            subDay.modifierDate = new Date();

            subOfTheDayCheck = insertSubOfTheDay(subOfDay);
            sujIds.add(subDay.id);
        }

        TimeTable timeTable;
        timeTable = tTableService.timeTable;
        timeTable._id = IDGenerator.subDocId();
        timeTable.subjectOfTheDays = sujIds;
        timeTable.schoolId = Auth.sessionSchoolId();
        timeTable.modifier = Auth.sessionUsersId();
        timeTable.modified = new Date();
        timeTable.lastModified = new Date();
        timeTableCheck = insertTimeTable(tTableService.timeTable);

        if(timeTableCheck && subOfTheDayCheck){
            checkResult = true;
        }
        return checkResult;
    }

    private boolean insertTimeTable(TimeTable timeTable){
        System.out.println("\n Time Table: "+timeTable.toString());
        timeTableDaoQuery.insert(timeTable);
        return checkResult = true;
    }


    private boolean insertSubOfTheDay(SubjectOfTheDay subjectOfTheDay){
        System.out.println("\n Subject Of The Day: "+subjectOfTheDay.toString());
        subOfTheDayDaoQuery.insert(subjectOfTheDay);
        return checkResult = true;
    }

    private List<SubjectPeriod> subPeriodProcess(List<SubjectPeriod> period){
        List<SubjectPeriod> subPeriod = new ArrayList<>();
        for ( SubjectPeriod sp: period) {
            SubjectPeriod subjectP;
            subjectP = sp;
            subjectP.id = IDGenerator.subDocId();
            subjectP.modified = Auth.sessionUsersId();
            subjectP.lastModified = new Date();
            subjectP.modifierDate = new Date();
            subPeriod.add(subjectP);
        }
        return subPeriod;
    }

    public boolean insertSubOfTheDay(List<SubjectOfTheDay> subOfTheDays){
        return true;
    }
}

