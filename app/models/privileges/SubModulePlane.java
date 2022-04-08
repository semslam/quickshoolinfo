package models.privileges;

import java.util.Date;

/**
 * Created by TRAVELDEN DEV on 09/04/2017.
 */
public class SubModulePlane {
    public long id;
    public String subModule; // schConfig => fee , grade, timetable
    public String basic;
    public String starter;
    public String professional;
    public String unlimited;
    public String modified;
    public Date modifiedDate;
    public Date lastModifiedDate;
}
