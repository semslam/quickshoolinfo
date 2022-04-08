package data_services.actors_record.guardian;

import api.auth.Auth;
import api.util.IDGenerator;
import dao_implimentation.GuardianDoaQuery;
import dao_implimentation.UserDaoQuery;
import models.enum_config.Roles;
import models.enum_config.Status;
import models.guardian_record.Guardian;
import models.Users;
import models_views.GuardianUsers;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju S on 30/12/2016.
 */
public class GuardianRecord {

    private GuardianDoaQuery guardianDoaQuery;
    private UserDaoQuery userDaoQuery;
    private GuardianUsers guardianUsers;
    private GuardianRecord guardianRecord;
    private Guardian guardian;
    private Users user;
    private boolean checkResult = false;

    @Inject
    public GuardianRecord(UserDaoQuery userDaoQuery,
                           GuardianDoaQuery guardianDoaQuery){
        this.userDaoQuery = userDaoQuery;
        this.guardianDoaQuery = guardianDoaQuery;
        this.guardianUsers = new GuardianUsers();
        this.guardian = new Guardian();
        this.user = new Users();
        this.guardianRecord = new GuardianRecord();
    }

    public GuardianRecord(){}

    public Guardian guardians;
    public Users users;


    public boolean insertGuardian(Guardian guardians){
            guardian = guardians;
            guardian.modifierDate = new Date();
            guardian.lastModified = new Date();
            guardian.modifier = Auth.sessionUsersId();
        return checkResult = guardianDoaQuery.insert(guardian);
    }

    public boolean insertNewGuardian(Guardian guardians,Users users){
        String token = IDGenerator.tokenCode();
        guardian = guardians;
        boolean checkResultG =  guardianInfo(guardian);
        boolean checkResultU = userInfo(users,guardian._id,token);
        if(!(checkResultG && checkResultU)){
            checkResult = true;
        }
        return checkResult;
    }

    public List<GuardianUsers> findAllGuardian(){
        List<GuardianUsers> guardianUsersList;
        System.out.println("Session GudId: "+Auth.sessionSchoolId() );

        List<Guardian> guardians = guardianDoaQuery.findAllById("{schoolId: '"+Auth.sessionSchoolId()+"'}");
        List<Users> userses = userDaoQuery.listAllById("{schoolId: '"+Auth.sessionSchoolId()+"' , role: '"+Roles.GUARDIAN.getValue()+"'}");
        if(guardians.size() != 0 && userses.size() != 0){
            guardianUsersList  = guardianUsers.convertGuardianData(userses,guardians);
            return guardianUsersList;
        }
        return new ArrayList<>();
    }

    public List<Guardian> getAllGuardian(){
        List<Guardian> guardians = guardianDoaQuery.findAllById("{schoolId: '"+Auth.sessionSchoolId()+"'}");
        if(guardians == null || guardians.size() == 0 ){
            guardians = new ArrayList<>();
        }
        return guardians;
    }

    public GuardianRecord findGuardianById(long id){
        GuardianRecord guardianRecord = new GuardianRecord();
        try{
            guardianRecord.guardians = guardianDoaQuery.find("{id: "+id+"}");
            guardianRecord.users = userDaoQuery.find("{id: "+id+" , role: '"+Roles.GUARDIAN.getValue()+"'}");
            System.out.println(guardianRecord.toString());
        }catch (Exception e){
            e.getMessage();
            return new GuardianRecord();
        }
        return guardianRecord;
    }

    public boolean subDeleteGuardianById(long id){
        if(!(id <=0)){
            user._id = id;
            user.status = Status.Deleted.getValue();
            user.modifier = Auth.sessionUsersId();
            user.lastModified = new Date();
            checkResult =  userDaoQuery.update("{schoolId: '"+Auth.sessionSchoolId()+"' , id: "+id+"}",user);
        }

        return checkResult;
    }

    private boolean guardianInfo(Guardian guardians){

        if(guardians != null){
            guardian = guardians;
            guardian.counter +=1;
            if(guardians._id <= 0){
                guardian.modifierDate = new Date();
                guardian.modifier = Auth.sessionUsersId();
                guardian.lastModified = new Date();
                guardianDoaQuery.insert(guardian);
                checkResult = true;
            }else{
                guardian.lastModified = new Date();
                guardianDoaQuery.upload("{id: "+guardian._id+"}",guardian);
                checkResult = true;
            }
        }
        return  checkResult;
    }

    private boolean userInfo(Users users,long usersID ,String tokenCode){

        user.role = Roles.GUARDIAN.getValue();
        user.schoolId = Auth.sessionSchoolId();
        user.counter +=1;
        if(usersID <= 0){
            // insert new user info
            user._id = usersID;
            user.tokenCode = tokenCode;
            user.password = tokenCode;
            user.userEmail = users.userEmail;
            user.status = Status.Pending.getValue();
            user.modifier = Auth.sessionUsersId();
            user.modifierDate = new Date();
            user.lastModified = new Date();
            checkResult =  userDaoQuery.insert(user);
            System.out.println(user.toString());
        }else{
            //user.id = Auth.userId();
            // update staff details
            user = users;
            user.lastModified = new Date();
            checkResult =  userDaoQuery.update("{id: "+user._id+"}",user);
        }
        return checkResult;
    }

}