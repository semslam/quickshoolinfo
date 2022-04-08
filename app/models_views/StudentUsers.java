package models_views;

import app_bizz.SchConfigIdToValueConverter;
import models.enum_config.Status;
import models.Users;
import models.education.config.ClassRoom;
import models.education.config.Department;
import models.education.config.Grade;
import models.staff_records.Staff;
import models.std_records.Student;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by TRAVELDEN DEV on 02/05/2017.
 */
public class StudentUsers {
    private SchConfigIdToValueConverter converter;

    public long id;
    public String schoolId;
    public String name;
    public String gender;
    public String grade;
    public String classRoom;
    public String department;
    public String teacher;
    public Date admissionDate;
    public long modifier;
    public Date modifiedDate;
    public Date lostModifiedDate;

    @Inject
    private StudentUsers(SchConfigIdToValueConverter converter){
        this.converter = converter;
    }
    public StudentUsers(){}


    public List<StudentUsers> convertStudentDate(List<Users> users,
                                                 List<Student> students,
                                                 List<Grade> grades,
                                                 List<ClassRoom> classRooms,
                                                 List<Department> departments,
                                                 List<Staff> staffs){
        List<StudentUsers> studentUsersList = new ArrayList<>();

        for (int i = 0; i < users.size() && i < students.size(); i++) {
            if(!users.get(i).status.equals(Status.Deleted.getValue()) ){
                StudentUsers studentUsers = new StudentUsers();
                studentUsers.id = users.get(i)._id;
                studentUsers.schoolId = users.get(i).schoolId;
                studentUsers.name = students.get(i).firstName +" "+  students.get(i).middleName+" "+ students.get(i).lastName;
                studentUsers.gender =  students.get(i).gender;
                studentUsers.grade =  converter.getGrade(students.get(i).grade,grades);
                studentUsers.classRoom =  converter.getClassRoom(students.get(i).classRoom,classRooms);
                studentUsers.department = converter.getDepartment(students.get(i).department,departments);
                studentUsers.teacher =  converter.getTeacher(students.get(i).teacher,staffs);
                studentUsers.admissionDate =  students.get(i).admissionDate;
                studentUsers.modifier =  students.get(i).modifier;
                studentUsers.modifiedDate =  students.get(i).modifierDate;
                studentUsers.lostModifiedDate =  students.get(i).lastModified;
                studentUsersList.add(studentUsers);
            }
        }
        return studentUsersList;
    }


}
