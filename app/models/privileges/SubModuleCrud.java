package models.privileges;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ibrahim Olanrewaju S on 09/04/2017.
 */
public class SubModuleCrud {
    public long id;
    public List<String> roles;// developer ;TeachStaff; NonTeachStaff; Admin; SupperAdmin; Student; Guardian
    public List<String> positions;
    public String subModuleAccess; // lock and unlock
    public String subModule;
    public int insert;
    public int update; // 1 for false and 0 for true
    public int delete;
    public int list;
    public int preview;
    public int click;
    public long modified;
    public Date modifiedDate;
    public Date lastModifiedDate;
}
