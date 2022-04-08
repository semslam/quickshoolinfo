package models.privileges;

import java.util.Date;

/**
 * Created by Ibrahim Olanrewaju S DEV on 18/04/2017.
 */
public class ClientsPrivilege {

    public long id;
    public String appModule;
    // this should stand as a class and collection
    public String subModule;
    public String appPlane;
    public String schGrade;
    public long modified;
    public Date modifiedDate;
    public Date lastModifiedDate;
}
