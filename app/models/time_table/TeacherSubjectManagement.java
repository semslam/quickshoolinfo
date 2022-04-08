package models.time_table;

import models.education.config.Subject;

import java.util.List;

/**
 * Created by TRAVELDEN DEV on 02/08/2017.
 */
public class TeacherSubjectManagement{
    public long id;
    public String schoolId;
    public long teacherId;
    public String teacherName;
    public String academyYear;
    public String grade;
    public String classRoomTeacher;
    public String department;
    public List<Subject> subjectTeachs;
    // get info from SubjectOfTheDay class
    public List<SubjectOfDPeriodDetail> subjectOfDPeriodDetails;

}
