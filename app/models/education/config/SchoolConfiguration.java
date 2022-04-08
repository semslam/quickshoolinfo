package models.education.config;

import models.MyModel;
import models.education.fees.Fee;
import models.education.fees.FeeDetail;
import models.time_table.TimeTable;
import org.jongo.marshall.jackson.oid.Id;

import java.util.Date;
import java.util.List;

public class SchoolConfiguration extends MyModel{

    @Id
    public String _id;
    public List<Grade> grades;
    public List<ClassRoom> classRooms;
    public List<Fee> fees;
    public List<FeeDetail> feeDetails;
    public List<Period> periods;
   // public Score score;
    public List<Attendance> attendances;
    public List<Subject> subjects;
    public List<Term> terms;
    public List<TimeTable> timeTables;
    public List<Position> positions;
    public List<ClassRoomType> classRoomTypes;
    public List<Department> departments;

    @Override
    public String toString() {
        return "SchoolConfiguration{" +
                ", grades=" + grades +
                ", classRooms=" + classRooms +
                ", fees=" + fees +
                ", feeDetails=" + feeDetails +
                ", periods=" + periods +
                ", attendances=" + attendances +
                ", subjects=" + subjects +
                ", terms=" + terms +
                ", timeTables=" + timeTables +
                ", positions=" + positions +
                ", classRoomTypes=" + classRoomTypes +
                ", departments=" + departments +
                '}';
    }
}
