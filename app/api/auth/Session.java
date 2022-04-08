package api.auth;

import java.io.Serializable;

/**
 * Created by Ibrahim Olanrewaju Semiu on 18/09/2016.
 */
public class Session implements Serializable {
    public String schoolId;
    public long userId;
    public long counter;

}
