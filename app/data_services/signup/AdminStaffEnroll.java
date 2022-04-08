package data_services.signup;

import api.auth.Auth;
import api.crypto.AESCrypt;
import dao_implimentation.SchConfigDaoQuery;
import dao_implimentation.SchoolDaoQuery;
import dao_implimentation.StaffDaoQuery;
import dao_implimentation.UserDaoQuery;
import data_services.sch_config.AutoConfigurationSetup;
import models.Contact;
import models.enum_config.Roles;
import models.enum_config.Status;
import models.staff_records.Staff;
import models.Users;
import models.education.School;
import models.education.config.SchoolConfiguration;

import javax.inject.*;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju on 01/09/2016.
 */
public class AdminStaffEnroll {

    private StaffDaoQuery staffDaoQuery;
    private SchoolDaoQuery schoolDaoQuery;
    private UserDaoQuery userDaoQuery;
    private SchConfigDaoQuery schConfigDaoQuery;
    public AdminStaffEnroll(){}

    @Inject
    public AdminStaffEnroll(UserDaoQuery userDaoQuery,
                             StaffDaoQuery staffDaoQuery,
                             SchoolDaoQuery schoolDaoQuery,
                             SchConfigDaoQuery schConfigDaoQuery){
        this.staffDaoQuery = staffDaoQuery;
        this.schoolDaoQuery = schoolDaoQuery;
        this.userDaoQuery = userDaoQuery;
        this.schConfigDaoQuery = schConfigDaoQuery;
    }

    public long id;
    public String password;
    public String re_password;
    public String school_name;
    public String role;
    public String comfirmCode;

    public String position;// Principal, Head master, teacher, PA  e.t.c
    public String title;
    public String first_name;
    public String last_name;
    public String gender;
    public Date dob;// date of birth
    public File adminImage;
    public List<String> schBranchName;

    public String email;// a school can have more than one email address
    public String fax;// provide fax number if possible
    public String website;// the web site url of a school

    public String approvedNum;// the approver number e.g LGB2735552
    public String religion;// believe of the school // Muslim , Christian M & C
    public String eduLevel;// add the choosing school sub code e.g KN: kindergarten, nursery
    public String eduMode; // e.g day school ,boding school or day and boding school
    public String schType; // international or local school
    public String schSchemer;// public or private
    public String plan;// add the choosing package code e.g PR: Professional
    public File schLogo;
    public List<Contact> contact;// School contact
    public List<Contact> contact_staffs;// School contact

    public void regAdminInfoAndSchoolInfo(AdminStaffEnroll adminReg){
        Users user = new Users();
        Staff staff = new Staff();
        School school = new School();
      //  SchConfigServices schConfigView = new SchConfigServices();
        user._id = Auth.sessionUsersId();
        /*user.school_branch = Util.listToString(Util.addList(adminReg.schBranchName));*/
        if(adminReg.password != null){
            try{ user.password = AESCrypt.encrypt(adminReg.password);}catch(Exception ex){ex.getMessage();}}
        System.out.println("previous: "+adminReg.password+" encrypt: "+user.password);
        System.out.println("get UsersID:"+ Auth.sessionUsersId() );
        System.out.println("get SchoolID:"+ Auth.sessionSchoolId() );
        user.designation = adminReg.position;
        user.role = Roles.SUPPER_ADMIN.getValue();
       // user. = adminReg.sub_package;
       // user.privilege ="All";
        user.modifier = Auth.sessionUsersId();
        user.status = Status.Demo.getValue();
        user.lastModified = new Date();

        staff._id = Auth.sessionUsersId();
        staff.title = adminReg.title;
        staff.first_name = adminReg.first_name;
        staff.last_name = adminReg.last_name;
        staff.gender = adminReg.gender;
        staff.dob = adminReg.dob;
        staff.contact = adminReg.contact_staffs;
        staff.modifier = Auth.sessionUsersId();
        staff.lastModified = new Date();

        school._id = Auth.sessionSchoolId();
        school.name = adminReg.school_name;
        school.approvedNumber = adminReg.approvedNum;
        school.email = adminReg.email;
        school.status = Status.Demo.getValue();
        school.eduGrade = adminReg.eduLevel;
        school.eduMode = adminReg.eduMode;
        school.website = adminReg.website;
        school.schReligion = adminReg.religion;
        school.schSchemer = adminReg.schSchemer;
        school.schType = adminReg.schType;
        school.contact = adminReg.contact;
        school.modifier = Auth.sessionUsersId();
        school.lastModified = new Date();

        SchoolConfiguration scg = new SchoolConfiguration();
        scg._id = Auth.sessionSchoolId();
        scg.modified = new Date();
        scg.lastModified = new Date();
        scg.modifier = Auth.sessionUsersId();
        scg.grades = AutoConfigurationSetup.getAllGrade(adminReg.eduLevel);

        userDaoQuery.update("{id: "+user._id+"}",user);
        staffDaoQuery.upload("{_id: "+staff._id+"}",staff);
        schoolDaoQuery.update("{_id: '"+school._id+"'}",school);
        // query the database if schConfig collection exist in db if exist insert new data else update add new data
        schConfigDaoQuery.insert(scg);

    }

    public void StaffEnrollment(){

    }

    public School schoolInfo(String query){
        School school = new School();
        school = schoolDaoQuery.find("{_id: '"+query+"'}");
        return school;
    }

    public void addPlan(AdminStaffEnroll admin){
        School school = new School();
        school._id = Auth.sessionSchoolId();
        school.appPlanePackage = admin.plan;
        schoolDaoQuery.update("{_id: '"+school._id+"'}",school);
    }


}
