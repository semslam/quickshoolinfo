package models.privileges;

import java.util.*;

/**
 * Created by Ibrahim Olanreaju O DEV on 29/12/2016.
 */
public class SchPrivilege {
    // this will be control by school administrator
    public long id;
    public String appModuleAccess; // lock and unlock
    public String appModule;
    public List<SubModuleCrud> subModuleCruds = new ArrayList<>();
    public String modified;
    public Date modifiedDate;
    public Date lastModifiedDate;
}


