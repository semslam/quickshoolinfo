package models.education.config;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by developer02 on 01/11/2016.
 */
public class Attendance {
    public long id;
    public String attendanceType;
    public Map<String,Integer> periodAndTimes; // period: day, month time: 1 ,2 ,3
   // public String authId;
    public String modifier;
    public Date modified;
    public Date lastModified;
    public int counter;


}
