package models.time_table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu on 11/05/2017.
 */
public class SubjectPeriod {

    public long id;
    public long periodId; // select from the period class and you can select more than one up to three
    public long subjectId;
    public long teacherId; // select the teacher that will teach the subject
    public long classRoom; //
    public String periodMode; // teaching , test, revision ,exam , brake
    public String subjectTopic; // the topic of the subject
    public String subjectKeyPoint;// the of the topic that will be touch the subject
    public long modified;
    public Date modifierDate;
    public Date lastModified;

    @Override
    public String toString() {
        return "SubjectPeriod{" +
                "id=" + id +
                ", periodId=" + periodId +
                ", subjectId='" + subjectId + '\'' +
                ", classRoom='" + classRoom + '\'' +
                ", periodMode='" + periodMode + '\'' +
                ", subjectTopic='" + subjectTopic + '\'' +
                ", subjectKeyPoint='" + subjectKeyPoint + '\'' +
                ", teacherId=" + teacherId +
                ", modified=" + modified +
                ", modifierDate=" + modifierDate +
                ", lastModified=" + lastModified +
                '}';
    }
}
