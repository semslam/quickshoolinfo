package models.privileges;

import java.util.Date;
import java.util.List;

/**
 * Created by TRAVELDEN DEV on 23/04/2017.
 */
public class UsersSubModuleCrud {
    public long id;
    public String subModule;
    public int insert;
    public int update; // 1 for false and 0 for true
    public int delete;
    public int list;
    public int preview;
    public int click;
    public String modified;
    public Date modifiedDate;
    public Date lastModifiedDate;
}
