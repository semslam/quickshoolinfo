package models;

import org.jongo.marshall.jackson.oid.Id;

import java.util.Date;

/**
 * Created by  Ibrahim Olanrewaju Abdulsemiu on 28/08/2017.
 */
public class Logger {
    @Id
    public long id;
    public String schoolId;
    public String loggerName;
    public Date modified;
}
