package controllers;

import data_services.actors_record.guardian.GuardianRecord;
import data_services.actors_record.staff.StaffRecord;
import data_services.actors_record.student.StudentRecord;

/**
 * Created by Ibrahim Olanrewaju Semiu on 26/06/2017.
 */
public class UsersCtr {
 private StaffRecord staffRecord;
 private StudentRecord studentRecord;
 private GuardianRecord guardianRecord;

    public UsersCtr(){
        this.staffRecord = new StaffRecord();
        this.studentRecord = new StudentRecord();
        this.guardianRecord = new GuardianRecord();
    }
}
