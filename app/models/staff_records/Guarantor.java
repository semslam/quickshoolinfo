package models.staff_records;

import java.util.Date;

/**
 * Created by Ibrahim Olanrewaju S on 24/02/2017.
 */
public class Guarantor {
    public long id;
    public String title;
    public String fullName;
    public String gender;
    public String maritalStatus;
    public String religion;
    public String residentAddress;
    public String personalPhone;
    public String personalEmail;
    public String relationStatus;
    public String relationshipYear;
    public String organisationName;
    public String businessAddress;
    public String businessPhone;
    public String jobTitle;
    public String jobLevel;

    @Override
    public String toString() {
        return "Guarantor{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", fullName='" + fullName + '\'' +
                ", gender='" + gender + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", religion='" + religion + '\'' +
                ", residentAddress='" + residentAddress + '\'' +
                ", personalPhone='" + personalPhone + '\'' +
                ", personalEmail='" + personalEmail + '\'' +
                ", relationStatus='" + relationStatus + '\'' +
                ", relationshipYear='" + relationshipYear + '\'' +
                ", organisationName='" + organisationName + '\'' +
                ", businessAddress='" + businessAddress + '\'' +
                ", businessPhone='" + businessPhone + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobLevel='" + jobLevel + '\'' +
                '}';
    }
}
