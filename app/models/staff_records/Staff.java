package models.staff_records;

import models.Bank;
import models.Contact;
import models.human_resources.EduBackGround;
import models.human_resources.WorkingExperience;
import models.privileges.UserPrivilege;
import org.jongo.marshall.jackson.oid.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju S on 02/09/2016.
 */
public class Staff {

    @Id
    public long _id;
    public String schoolId;
    public String enrollmentNo;
    public String title;
    public String first_name;
    public String middle_name;
    public String last_name;
    //public String position;// Principal, Head master, teacher, PA  e.t.c// not needed here
    public String gender;
    public String religion;
    public Date dob;// date of birth
    public String maritalStatus; // married or not
    public long department;
    public String speciallization;
    public String teachersType;// part time / full time
    public String stateOfOrigin;//state Of Origin
    public long classRoomTeacher;
    public String nationality;
    public String socialSecurityNo; // maybe if from US
    public Date enrollmentDate;
    // first part
    public List<Long> subjectTeaching;
    public List<Long> classTeacher; // change to classTeaching
   // public Map(String, String) classAndSubject;
    public List<Bank> banks; // teachers bank account details// this field stand as a class
    public List<String> skills;// reading , sport
    public List<WorkingExperience> workingExperience;// working experience
    public List<EduBackGround> eduBackground;// educational background
    public List<Guarantor> guarantors;
    public List<Contact> contact;
    public long modifier;
    public Date modifierDate;
    public Date lastModified;
    public int counter;

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + _id +
                ", schoolId='" + schoolId + '\'' +
                ", enrollmentNo='" + enrollmentNo + '\'' +
                ", title='" + title + '\'' +
                ", first_name='" + first_name + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", gender='" + gender + '\'' +
                ", religion='" + religion + '\'' +
                ", dob=" + dob +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", department='" + department + '\'' +
                ", teachersType='" + teachersType + '\'' +
                ", stateOfOrigin='" + stateOfOrigin + '\'' +
                ", classRoomTeacher='" + classRoomTeacher + '\'' +
                ", nationality='" + nationality + '\'' +
                ", socialSecurityNo='" + socialSecurityNo + '\'' +
                ", enrollmentDate=" + enrollmentDate +
                ", subjectTeaching=" + subjectTeaching +
                ", classTeacher=" + classTeacher +
                ", banks=" + banks +
                ", skills=" + skills +
                ", workingExperience=" + workingExperience +
                ", eduBackground=" + eduBackground +
                ", guarantors=" + guarantors +
                ", contact=" + contact +
                ", modifier=" + modifier +
                ", modifierDate=" + modifierDate +
                ", lastModified=" + lastModified +
                ", counter=" + counter +
                '}';
    }
}
