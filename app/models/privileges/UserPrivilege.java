package models.privileges;

import java.util.*;

/**
 * Created by TRAVELDEN DEV on 09/04/2017.
 */
public class UserPrivilege {
    // this will be control by user staff
    public long id;
    public Date timePeriod;
    public String appModule;
    public List<UsersSubModuleCrud> usersSubModuleCruds = new ArrayList<>();
    public String modified;
    public Date modifiedDate;
    public Date lastModifiedDate;
}
