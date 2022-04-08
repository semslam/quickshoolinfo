package data_services;

import api.auth.Auth;
import dao_implimentation.StaffDaoQuery;
import dao_implimentation.UserDaoQuery;
import models.Users;
import models.privileges.UserPrivilege;
import models.staff_records.Staff;

import javax.inject.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TRAVELDEN DEV on 17/04/2017.
 */
public class UserPrivilegeServices {

    private Users users;
    private UserDaoQuery userDaoQuery;
    private boolean checkResult;
    @Inject
    private UserPrivilegeServices(UserDaoQuery userDaoQuery){
        this.users = new Users();
        this.userDaoQuery = userDaoQuery;
    }

    public UserPrivilegeServices(){}

    public boolean updateUserPrivilege(Users userPrivilege){
        List<UserPrivilege> uPrivileges = new ArrayList<>();

        if (userPrivilege != null){
            String query = "{id:"+ userPrivilege._id+"}";

            userDaoQuery.update(query,userPrivilege);
            checkResult = true;
        }else{
            checkResult = true;
        }
        return checkResult;
    }

   /* public Staff findStaffPrivilegeById(String query){
        return userDaoQuery.findOne(query);
    }*/

    public Users findAllStaffPrivilege(){
        String query = "{id: '"+Auth.sessionSchoolId()+"'},{id: 1, schoolId: 1, userPrivileges: 1}";

        try{
            users = userDaoQuery.find(query);
        }catch (Exception ioe){
            ioe.getMessage();
        }
        return users;
    }
}
