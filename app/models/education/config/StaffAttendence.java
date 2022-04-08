package models.education.config;

import java.util.Date;
import java.util.Map;

public class StaffAttendence {

	public  String Id;
	public String attendanceType;
	public String staffId;
	public String[] classId;
	public String present;
	public Map<String,String> absentAndExcuse; // absent with an excuse
	public Map<String,String> tardyAndExcuse; // late with an excuse
	public String[] days; // monday, friday
	public String modifier;
	public Date modified;
	public Date lastModified;
	public int counter;

}
