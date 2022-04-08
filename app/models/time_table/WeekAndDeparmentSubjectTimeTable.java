package models.time_table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by TRAVELDEN DEV on 11/05/2017.
 */
public class WeekAndDeparmentSubjectTimeTable {

    public long id;
    public long SubjectOfTheDayId;
    public String subjectType; // test , exam , teaching, revission
    public int week;//number of the week
    public String department;// it can be class or department
    public String desciption; // this will talk about what going on that week time table
    public List<SubjectOfTheDay> subjectOfTheDays = new ArrayList<>();
    public long modified;
    public Date modifierDate;
    public Date lastModified;

}
