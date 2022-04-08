package models.time_table;

import java.util.Date;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu on 11/05/2017.
 */
public class SubjectOfTheDay {

    public long id;
    public String schoolId;
    public String days; // the days of the week e.g monday tuesday wednesday thursday friday saturday sunday
    public String academicYear;
    public List<SubjectPeriod> subjectPeriods; // select from the subjectPeriod class // contain info of subject and period
    public long modified;
    public Date modifierDate;
    public Date lastModified;

    @Override
    public String toString() {
        return "SubjectOfTheDay{" +
                "id=" + id +
                ", schoolId='" + schoolId + '\'' +
                ", days='" + days + '\'' +
                ", academicYear='" + academicYear + '\'' +
                ", subjectPeriods=" + subjectPeriods +
                ", modified=" + modified +
                ", modifierDate=" + modifierDate +
                ", lastModified=" + lastModified +
                '}';
    }
}
