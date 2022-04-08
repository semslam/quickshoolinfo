package models.time_table;

import org.jongo.marshall.jackson.oid.Id;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class TimeTable {
	// subject timeTable
	@Id
	public long _id;
	public String schoolId;
	public List<String> classRoom;
    public List<String> grade;
    public String department;
	public String academicYear;
    public String description;
   // public List<String> sessionId;
    /* public Date startDate;
    public Date endDate;*/
    public List<Long> subjectOfTheDays;// subject of the day id
    public long modifier;
    public Date modified;
    public Date lastModified;
	public int counter;

	@Override
	public String toString() {
		return "TimeTable{" +
				"id=" + _id +
				", schoolId='" + schoolId + '\'' +
				", classRoom=" + classRoom +
				", grade=" + grade +
				", academicYear='" + academicYear + '\'' +
				", subjectOfTheDays=" + subjectOfTheDays +
				", department='" + department + '\'' +
				", description='" + description + '\'' +
				", modified=" + modified +
				", modifier='" + modifier + '\'' +
				", counter=" + counter +
				'}';
	}
}
