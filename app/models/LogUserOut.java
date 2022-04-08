package models;

import org.jongo.marshall.jackson.oid.Id;

/**
 * Created by Ibrhaim Olanrewaju Abdulsemiu on 04/09/2017.
 */
public class LogUserOut extends MyModel{

    // this class is use to manipulate the time a specific users can login or logout e.g staff , student and guardian
    // this to restrict users login on wanted period
    @Id
    public long id;
    public String signIn;
    public String signOut;
    public String role;
    public String status;
}
