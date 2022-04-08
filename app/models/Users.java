package models;

import models.privileges.UserPrivilege;
import org.jongo.marshall.jackson.oid.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu on 08/09/2016.
 */
public class Users {
    @Id
    public long _id;
    public String schoolId;
    public String userEmail;
    public String password;
    public String schoolBranch;//not need here
    public String role;
    public String designation;// Principal, Head master, teacher, PA  e.t.c position also stand for designation
	public String status;// not-activate = New, dis-activate = InActivate, activate = Activate
    public String tokenCode;
    public List<UserPrivilege> userPrivileges;//
    public long modifier;
	public Date modifierDate;
    public Date lastModified;
    public int counter;

    @Override
    public String toString() {
        return "Users{" +
                "_id=" + _id +
                ", userEmail='" + userEmail + '\'' +
                ", password='" + password + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", schoolBranch='" + schoolBranch + '\'' +
                ", role='" + role + '\'' +
                ", designation='" + designation + '\'' +
                ", status='" + status + '\'' +
                ", tokenCode='" + tokenCode + '\'' +
                ", userPrivileges=" + userPrivileges +
                ", modifier=" + modifier +
                ", modifierDate=" + modifierDate +
                ", lastModified=" + lastModified +
                ", counter=" + counter +
                '}';
    }
}
