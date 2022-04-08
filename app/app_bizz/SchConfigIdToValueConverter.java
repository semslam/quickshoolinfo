package app_bizz;

import models.education.config.ClassRoom;
import models.education.config.Department;
import models.education.config.Grade;
import models.education.config.Subject;
import models.staff_records.Staff;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TRAVELDEN DEV on 28/09/2017.
 */
public class SchConfigIdToValueConverter {

    public String getTeacher(long id, List<Staff> staffs){
        String value = "";
        if(id !=0 || staffs == null && staffs.size() !=0){
            for(Staff staff : staffs){
                if(id == staff._id){
                    value = staff.first_name + staff.last_name;
                }
            }
        }
        return  value;
    }
    public String getDepartment(long id, List<Department> departments){
        String value = "";
        if(id !=0 || departments == null && departments.size() !=0){
            for(Department department : departments){
                if(id == department._id){
                    value = department.name;
                }
            }
        }
        return  value;
    }

    public String getClassRoom(long id, List<ClassRoom> classRooms){
        String value = "";
        if(id <= 0 || classRooms == null && classRooms.size() !=0){
            for(ClassRoom classRoom : classRooms){
                if(id == classRoom.id){
                    value = classRoom.className;
                }
            }
        }
        return  value;
    }
    public String getGrade(long id,List<Grade> grades){
        String value = "";
        if(id !=0 || grades == null && grades.size() !=0){
            for(Grade grade : grades){
                if(id == grade.id){
                    value = grade.name;
                }
            }
        }
        return  value;
    }

    public String getSubject(long id,List<Subject> subjects){
        String value = "";
        if(id !=0 || subjects == null && subjects.size() !=0){
            for(Subject subject : subjects){
                if(id == subject.id){
                    value = subject.subjectName;
                }
            }
        }
        return  value;
    }

    public List<String> getClassRoomList(List<Long> id,List<ClassRoom> classRooms){
        List<String> value = new ArrayList<>();
        int count = 0;
        if(id.size() !=0 || classRooms == null && classRooms.size() !=0){
            for(ClassRoom classRoom : classRooms){
                if(id.get(count) == classRoom.id){
                    value.add(classRoom.className);
                }
                id.remove(count);
                count +=1;
            }
            return value;
        }
        return new ArrayList<>();
    }

    public List<String> getSubjectList(List<Long> id,List<Subject> subjects){
        List<String> value = new ArrayList<>();
        int count = 0;
        if(id.size() !=0 || subjects == null && subjects.size() !=0){
            for(Subject subject : subjects){
                if(id.get(count) == subject.id){
                    value.add(subject.subjectName);
                }
                id.remove(count);
                count +=1;
            }
            return value;
        }
        return new ArrayList<>();
    }
}
