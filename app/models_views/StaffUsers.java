package models_views;

import app_bizz.SchConfigIdToValueConverter;
import models.enum_config.Status;
import models.Users;
import models.education.config.ClassRoom;
import models.education.config.Department;
import models.education.config.Subject;
import models.staff_records.Staff;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju  Abdulsemiu on 02/05/2017.
 */
public class StaffUsers{
    private SchConfigIdToValueConverter converter;
    public long id;
    public String schoolId;
    public String imageUrl;
    public String name;
    public String role;
    public String classRoomTeacher;
    public String position;
    public List<String> subjects;
    public List<String> classTeaching;
    public String department;
    public long modifier;
    public Date modifiedDate;

    public StaffUsers(){
        converter =new SchConfigIdToValueConverter();
    }
    public List<StaffUsers> convertStaffData(List<Users> users, List<Staff> staffs,
                                             List<ClassRoom> classRooms,
                                             List<Subject> subjects,
                                             List<Department> departments
                                             ){
        List<StaffUsers> staffUserses = new ArrayList<>();
        for (int i = 0; i < users.size() && i < staffs.size(); ++i) {
            if(!users.get(i).status.equals(Status.Deleted.getValue())){
                StaffUsers staffUsers = new StaffUsers();
                staffUsers.id =  users.get(i)._id;
                staffUsers.schoolId = users.get(i).schoolId;
                staffUsers.role = users.get(i).role;
                staffUsers.modifiedDate = users.get(i).modifierDate;

                staffUsers.name = staffs.get(i).title + " "+ staffs.get(i).first_name +" "+ staffs.get(i).last_name;
                staffUsers.position = "N/A"; //user.position;
                staffUsers.classRoomTeacher = converter.getClassRoom(staffs.get(i).classRoomTeacher,classRooms);
                staffUsers.subjects = converter.getSubjectList(staffs.get(i).subjectTeaching,subjects);
                staffUsers.classTeaching = converter.getClassRoomList(staffs.get(i).classTeacher,classRooms) ;
                staffUsers.department = converter.getDepartment(staffs.get(i).department, departments);
                staffUserses.add(staffUsers);
            }
        }
        return staffUserses;
    }

}
