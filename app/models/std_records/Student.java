package models.std_records;

/*
 * File   : Student.java
 * Date   : 2016
 * Version: 1.00
 * Author : Ibrahim Olanrewaju Semiu
 *  Modified Date : feb 17 2016
 * Copyright (c) 2016
 */

import models.Contact;
import models.education.config.Image;
import models.medical.Health;
import models.privileges.UserPrivilege;
import org.jongo.marshall.jackson.oid.Id;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Student {
    @Id
    public long _id;
    public String schoolId;
    public String firstName;
    public String middleName;
    public String lastName;
    public String gender;
    public Date dob;// date of birth
    public List<Long> guardianId;
    public List<Long> siblings;// sibling to know if the student have any brothers and sisters in that school
    //public Image photo;
    public String studentType;//staff student; scholarships student, fee etc
    public String admissionNo; // admission number // specific id generate
    public Date admissionDate;
    public long department;
    public String academicSession;// Academic Session sought // first time second time and third tame
    public String stateOfOrigin;
    public String nationality;
    public String socialSecurity;
    public long classRoom;// student grade this mean the student class
    public long grade;
    public List<Long> subjects;
    public long teacher;
    public String religion;// Muslim , Christian, traditional or null religion
    public String active;// Graduate, Left school, Expelled in school, active
    public boolean action;// maybe he/she is under suspension or sick
    // public StdClassInfo stdClassInfo
    public String motherTongue; // mother's tongue
    // contact can be ArrayList or Vector because of the multi contact
    public List<Contact> contact;
    public Health health;
    public long modifier;
    public Date modifierDate;
    public Date lastModified;
    public int counter;

    public Student(){}

    @Override
    public String toString() {
        return "Student{" +
                "id=" + _id +
                ", schoolId='" + schoolId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", dob=" + dob +
                ", guardianId=" + guardianId +
                ", siblings=" + siblings +
                ", studentType='" + studentType + '\'' +
                ", admissionNo='" + admissionNo + '\'' +
                ", admissionDate=" + admissionDate +
                ", department='" + department + '\'' +
                ", academicSession='" + academicSession + '\'' +
                ", stateOfOrigin='" + stateOfOrigin + '\'' +
                ", nationality='" + nationality + '\'' +
                ", socialSecurity='" + socialSecurity + '\'' +
                ", classRoom='" + classRoom + '\'' +
                ", teacher='" + teacher + '\'' +
                ", religion='" + religion + '\'' +
                ", active='" + active + '\'' +
                ", action=" + action +
                ", motherTongue='" + motherTongue + '\'' +
                ", contact=" + contact +
                ", health=" + health +
                ", modifier=" + modifier +
                ", modifierDate=" + modifierDate +
                ", lastModified=" + lastModified +
                ", counter=" + counter +
                '}';
    }
}
