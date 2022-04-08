package models.privileges;

import java.util.*;

/**
 * Created by Ibrahim Olanrewaju S on 29/12/2016.
 */
public class ApplicationPrivilege {
    // this will be control by subscribe plane
    public long id;
    public String appModule; // schConfig, online exam
    public List<SubModulePlane> subModulePlanes = new ArrayList<>();
    public List<String> role;// developer; supper-admin; admin :only
    public List<String> schGrades;// nur; pri; sec
    public String modified;
    public Date modifiedDate;
    public Date lastModifiedDate;
   // application privilege will be add to school collection
}
