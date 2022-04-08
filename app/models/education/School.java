/*
 * File   : School.java
 * Date   : 2016
 * Version: 1.00
 * Author : Ibrahim Olanrewaju Semiu
 * Modified Date : feb 17 2016
 * Copyright (c) 2016
 */

package models.education;

import models.Contact;
import models.LogUserOut;
import models.MailServerDetails;
import models.WorkingDays;
import models.education.config.*;
import models.privileges.SchPrivilege;
import org.jongo.marshall.jackson.oid.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class School {
    @Id
	public String _id;
    public String name;// the name of the school
    public String abbreviation;
    public String subDomain; // e.g www.brownbana.qusi.com
    public String motto;
    public String title;
    public String meterData;
    public String keyword;
    public String description;
    public String website;// the web site url of a school
    public String email;
   // public String appAccess; // app access lock and unlock
    public String schTokenPaymentId; //unique token
    public String schToken; // dynamic token
    public boolean approved;// approved //non_approve
	public String regNumber;// registration number
    public String approvedNumber;// the approver number e.g LGB2735552
    public String schReligion;// religion of the school // Muslim , Christian M & C
    public String eduGrade;// add the choosing school sub code e.g KN: kindergarten, nursery K-Basic : Higher-Education // change dis field to institution type
    public String eduMode; // e.g day school ,boding school or day and boding school
    public String schType; // international or domestic school
    public String language;
    public String status;
    public String currency;
    public String timeZone;
    public String themesColor;
    public String font;
   // public String logoutAfter; // auto logout user
   // public String userApplicableFor; // controlling the user that use the application e.g staff , student, guardian
    public List<LogUserOut> logUserOuts; // this class control the login process of the user
    public String staffAutoGenEnrollNo;// staff auto generating enrollment number
    public String stdAutoGenAdmissionNo;// student auto generating admission number
    public String startDayOfTheWeek; // this field is no long needed
    public List<String> workingDayses; // working days of the week // this field is no longer needed
    public List<WorkingDays> workingDays;
    public List<String> branchName;// branch will stand out as a class and contact
    public String schSchemer;// public or private enum class
    public Date foundedDate;
	public String appPlanePackage;// add the choosing package code e.g PR: Professional
    public List<AcademySession> academySessions;
	public List<Contact> contact;// School contact
    public MailServerDetails mailServerDetails;
    public SchoolConfiguration schoolConfig;
    public String teamsAndConditions;
    public List<SchPrivilege> schPrivileges;
    public long modifier;
    public Date modifierDate;
    public Date lastModified;
	public int counter;



}
