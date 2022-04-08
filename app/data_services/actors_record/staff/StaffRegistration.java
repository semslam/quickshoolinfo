package data_services.actors_record.staff;

import api.auth.Auth;
import api.util.IDGenerator;
import com.google.inject.Inject;
import dao_implimentation.SchoolDaoQuery;
import dao_implimentation.StaffDaoQuery;
import dao_implimentation.UserDaoQuery;
import models.*;
import models.enum_config.Roles;
import models.education.config.ClassRoom;
import models.human_resources.EduBackGround;
import models.human_resources.WorkingExperience;
import models.staff_records.Staff;

import java.util.Date;
import java.util.List;

/**
 * Created by developer02 on 23/10/2016.
 */
public class StaffRegistration {

    private StaffDaoQuery staffDaoQuery;
    private SchoolDaoQuery schoolDaoQuery;
    private UserDaoQuery userDaoQuery;

    public StaffRegistration(){}

    @Inject
    public StaffRegistration(StaffDaoQuery staffDaoQuery,UserDaoQuery userDaoQuery){
    	this.staffDaoQuery = staffDaoQuery;
    	this.userDaoQuery = userDaoQuery;
    }

    public Users users;
    public Staff staff;
    public ClassRoom classRoom;
    public WorkingExperience workingExperience;// working experience
    public EduBackGround eduBackground; // educational background
    public List<Contact> contact;


	public void insert(StaffRegistration staffRegistration){
		Users users  = new Users();
		Staff staff = new Staff();

		Long genericId = IDGenerator.userIdGenerator();
        users._id = genericId;
        users.role = Roles.SUPPER_ADMIN.getValue();
        users.schoolId = Auth.sessionSchoolId();
        users.schoolBranch = Auth.getSessionSchoolBranch();
        users = staffRegistration.users;
        users.modifier = Auth.sessionUsersId();
        users.modifierDate = new Date();
        users.lastModified = new Date();
        users.counter +=1;

        staff._id = genericId;
        staff = staffRegistration.staff;
       /* if(staffRegistration.workingExperience.size() != 0 || staffRegistration.eduBackground.size() != 0 ){
            for(WorkingExperience workingExperience : staffRegistration.workingExperience){
                staff.workingExperience = staffRegistration.workingExperience;
            }
            for(EduBackGround eduBackground : staffRegistration.eduBackground ){
                staff.eduBackground = staffRegistration.eduBackground;
            }
        }*/
        staff.contact = staffRegistration.contact;
        staff.modifier = Auth.sessionUsersId();
        staff.modifierDate = new Date();
        staff.lastModified = new Date();
        staff.counter +=1;

        userDaoQuery.insert(users);
        staffDaoQuery.insert(staff);

	}

}
