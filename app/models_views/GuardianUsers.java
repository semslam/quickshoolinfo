package models_views;

import models.enum_config.Status;
import models.Users;
import models.guardian_record.Guardian;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ibrahim Olanreaju S  on 02/05/2017.
 */
public class GuardianUsers{

    public long id; // school id stand as guardian
    public String schoolId;
    public String name;
    public String relationship;// father, mother
    public String occupation;
    public List<Long> studentId;// = new ArrayList<>();//  a parent can have more than one student in a school
    public String gender;
    public String religion;
    public String tribe;
    public long modifier;
    public Date modified;

    public List<GuardianUsers> convertGuardianData(List<Users> users, List<Guardian> guardianList){
        List<GuardianUsers> guardianUserses = new ArrayList<>();
        for (int i = 0; i < users.size() && i < guardianList.size(); ++i) {
            if(!users.get(i).status.equals(Status.Deleted.getValue())){
                GuardianUsers guardianUsers = new GuardianUsers();
                guardianUsers.id =  users.get(i)._id;
                guardianUsers.schoolId = users.get(i).schoolId;
                guardianUsers.modified = users.get(i).modifierDate;

                guardianUsers.name = guardianList.get(i).title + " "+ guardianList.get(i).firstName +" "+ guardianList.get(i).lastName;
                guardianUsers.occupation = guardianList.get(i).occupation;
                guardianUsers.studentId = guardianList.get(i).studentId;
                guardianUsers.gender = guardianList.get(i).gender;
                guardianUsers.relationship = guardianList.get(i).relationship;
                guardianUsers.tribe = guardianList.get(i).tribe;
                guardianUsers.religion = guardianList.get(i).religion;
                guardianUserses.add(guardianUsers);
            }
        }
        return guardianUserses;
    }


}
