package models;

import org.jongo.marshall.jackson.oid.Id;

import java.util.Date;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu DEV on 28/08/2017.
 */
public class LoginSession extends  MyModel{
    @Id
    public long _id; // user id
    public String schoolId;
    public String status;
    public String onlineStatus; // online, offline, away, doNotDisturb, invisible
    public String role;

}
