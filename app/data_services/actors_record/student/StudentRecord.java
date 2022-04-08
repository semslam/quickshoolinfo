package data_services.actors_record.student;

import api.auth.Auth;
import api.util.IDGenerator;
import com.google.inject.Inject;
import dao_implimentation.*;
import data_services.actors_record.guardian.GuardianRecord;
import data_services.sch_config.SchConfigServices;
import models.enum_config.Roles;
import models.enum_config.Status;
import models.guardian_record.Guardian;
import models.std_records.Student;
import models.Users;
import models_views.StudentUsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created Ibrahim Olanrewaju Abdulemiu on 18/10/2016.
 */
public class StudentRecord {
    private StaffDaoQuery staffDaoQuery;
    private SchConfigServices schConView;
    private  UserDaoQuery userDaoQuery;
    private  GuardianDoaQuery guardianDoaQuery;
    private  GuardianRecord gudRrd;
    private  StudentDoaQuery studentDoaQuery;
    private StudentUsers studentUsers;
    private Student student;
    private Users user;
    private  boolean checkResult = false;

@Inject
public StudentRecord(
                StaffDaoQuery staffDaoQuery,
                SchConfigServices schConView,
                UserDaoQuery userDaoQuery,
                StudentDoaQuery studentDoaQuery,
                GuardianDoaQuery guardianDoaQuery){
    this.staffDaoQuery = staffDaoQuery;
    this.userDaoQuery = userDaoQuery;
    this.studentDoaQuery = studentDoaQuery;
    this.guardianDoaQuery = guardianDoaQuery;
    this.gudRrd = new GuardianRecord();
    this.student = new Student();
    this.user = new Users();
    this.studentUsers = new StudentUsers();
    this.schConView = schConView;
}

public StudentRecord(){}

    public Users users;
    public Student students;
    public Guardian guardians;

    public boolean insertStudent(StudentRecord admission){
        Guardian guardian = new Guardian();
        long genericId = IDGenerator.userIdGenerator();
        String tokenCode = IDGenerator.tokenCode();
        long guardianId = IDGenerator.userIdGenerator();
        guardian = admission.guardians;
        //if(!(admission.guardians.studentId.size() <=0)){
            guardian.studentId = Arrays.asList(genericId);
        //}
        admission.guardians._id = guardianId;
        admission.students.guardianId = Arrays.asList(guardianId);
        boolean checkReturnU =  userInfo(admission, genericId,tokenCode);
        boolean checkReturnS = studentInfo(admission, genericId);
        boolean checkReturnG =  studentGuardianInfo(guardian);
        //boolean checkReturnG =  gudRrd.guardianInfoWithStudId(guardian);
        if(!(checkReturnG && checkReturnS && checkReturnU)){
            return checkResult = true;
        }

        return checkResult;
    }

    public boolean updateStudent(StudentRecord admission){
         checkResult = studentInfo(admission,0);
        if(!checkResult){
           checkResult = true;
        }
        return checkResult;
    }

    private boolean userInfo(StudentRecord stdR,Long usersID ,String tokenCode){
        user.counter +=1;
        if(stdR.students._id <= 0){
            user.role = Roles.STUDENT.getValue();
            user.status = Status.Pending.getValue();
            user.schoolId = Auth.sessionSchoolId();
            user.tokenCode = tokenCode;
            user.password = tokenCode;
            user.userEmail = tokenCode;
            user.modifier = Auth.sessionUsersId();
            user.modifierDate = new Date();
            user.lastModified = new Date();
            // insert new user info
            user._id = usersID;
          checkResult =  userDaoQuery.insert(user);
            System.out.println(user.toString());
        }else{
            user._id = Auth.sessionUsersId();
            // update staff details
            user.lastModified = new Date();
          checkResult =  userDaoQuery.update("{_id: "+user._id+"}",user);
        }
        return checkResult;
    }

    private boolean studentInfo(StudentRecord stdR,long usersId){
        if(stdR != null){
            student = stdR.students;
            student.counter +=1;
            if(stdR.students._id <= 0){
                student._id = usersId;
                student.schoolId = Auth.sessionSchoolId();
                student.modifier = Auth.sessionUsersId();
                student.modifierDate = new Date();
                student.lastModified = new Date();
                checkResult =  studentDoaQuery.insert(student);
                System.out.println(student.toString());
            }else{
                student.modifier = Auth.sessionUsersId();
                student.lastModified = new Date();
              checkResult =  studentDoaQuery.upload("{id: "+student._id+"}",student);
            }
        }
        return checkResult;
    }

    private boolean studentGuardianInfo(Guardian guardian){
        long guardianId = IDGenerator.userIdGenerator();
        boolean checkResultG = userGuardianInfo(guardianId, guardian.contact.get(0).email);
        boolean checkResultU = guardianInfo(guardian);
        if(!checkResultG && checkResultU){
            checkResult = true;
        }
        return checkResult;
    }

    private boolean userGuardianInfo(long usersID, String email){
        String tokenCode = IDGenerator.tokenCode();
        user.role = Roles.GUARDIAN.getValue();
        user.schoolId = Auth.sessionSchoolId();
        user.counter +=1;
            // insert new user info
        user._id = usersID;
        user.tokenCode = tokenCode;
        user.password = tokenCode;
        user.userEmail = email;
        user.status = Status.Pending.getValue();
        user.modifier = Auth.sessionUsersId();
        user.modifierDate = new Date();
        user.lastModified = new Date();
        checkResult =  userDaoQuery.insert(user);
        System.out.println(user.toString());
        return checkResult;
    }

    private boolean guardianInfo(Guardian guardians){
        Guardian guardian = new Guardian();
        guardian = guardians;
        guardian.modifierDate = new Date();
        guardian.lastModified = new Date();
        guardian.modifier = Auth.sessionUsersId();
        return checkResult = guardianDoaQuery.insert(guardian);
    }

    // add new student info, next to student contact, next to health info, next to guardian info

    public List<StudentUsers> findAllStudent(){
        String schId = Auth.sessionSchoolId();
        List<StudentUsers> studentUserses;
        List<Student> students = studentDoaQuery.findAllById("{schoolId: '"+schId+"'}");
        List<Users> userses = userDaoQuery.listAllById("{schoolId: '"+schId+"' , role: '"+Roles.STUDENT.getValue()+"' }");
        if(students.size() != 0 && userses.size() != 0){
            studentUserses  = studentUsers.convertStudentDate(userses,
                    students,schConView.getAllGrade(schId),
                    schConView.getAllClassRoom(schId),
                    schConView.getAllDepartment(schId),
                    staffDaoQuery.findAllById("{schoolId: '"+schId+"'}")
            );
            return studentUserses;
        }
        return new ArrayList<>();
    }

    public List<Student> getAllStudent(){
        List<Student> students = studentDoaQuery.findAllById("{schoolId: '"+Auth.sessionSchoolId()+"'}");
        if(students == null || students.size() ==0){
            students = new ArrayList<>();
        }
        return students;
    }

    public StudentRecord  findStudentById(long id){
        StudentRecord sr = new StudentRecord();
        try{
          sr.students =  studentDoaQuery.find("{schoolId: '"+Auth.sessionSchoolId()+"' , id: "+id+"}");
          sr.users =  userDaoQuery.find("{ schoolId: '"+Auth.sessionSchoolId()+"', id: "+id+" , role: '"+Roles.STUDENT.getValue()+"'}");
            // pool guardian info are
        }catch (Exception e){
            e.getMessage();
            return new StudentRecord();
        }
        return sr;
    }

    public boolean subDeleteStudentById(long id){
        if(!(id <=0)){
            user._id = id;
            user.status = Status.Deleted.getValue();
            user.modifier = Auth.sessionUsersId();
            user.lastModified = new Date();
            checkResult =  userDaoQuery.update("{schoolId: '"+Auth.sessionSchoolId()+"' , id: "+id+"}",user);
        }

        return checkResult;
    }
}