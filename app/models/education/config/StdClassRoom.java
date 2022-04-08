package models.education.config;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Ibrahim Olanrewaju S on 07/01/2017.
 */
public class StdClassRoom {
    public String classId;
    public String classRoomId;
    public int numberOfMale;// number of male in the class // student class room
    public int numberOfFemale; // number of female in the class // student class room
    public int totalOfStudent; // total number of students in the class // student class room
    public Map<String,Map<String,List<String>>> tdTypeSub;// teacher's type as key eg class td sub td; sub & td as Value td as key sub as value fee_master
    public String modifier;
    public Date modified;
    public Date lastModified;
    public int counter;
}
