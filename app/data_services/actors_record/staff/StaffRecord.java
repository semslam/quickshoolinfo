/**
 * Created by Ibrahim Olanrewaju Semiu on 23/12/2016.
 */
package data_services.actors_record.staff;

import api.auth.Auth;
import api.util.DateFormat;
import api.util.IDGenerator;
import com.google.inject.Inject;
import dao_implimentation.StaffDaoQuery;
import dao_implimentation.UserDaoQuery;
import data_services.sch_config.SchConfigServices;
import models.*;
import models.enum_config.Status;
import models.staff_records.Staff;
import models_views.StaffUsers;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class StaffRecord {
    public Staff staffs;
    public Users users;

    private SchConfigServices schConView;
    private UserDaoQuery userDaoQuery;
    private StaffDaoQuery staffDaoQuery;
    private StaffUsers staffUsers;
    private Staff staff;
    private Users user;
    private boolean checkResult = false;



    //contact
    @Inject
    public StaffRecord(StaffDaoQuery staffDaoQuery,
                       UserDaoQuery userDaoQuery,
                       SchConfigServices schConView){
        this.staff = new Staff();
        this.user = new Users();
        this.staffUsers = new StaffUsers();
        this.userDaoQuery  = userDaoQuery;
        this.staffDaoQuery = staffDaoQuery;
        this.schConView = schConView;
    }

    public StaffRecord (){}

    public boolean insertStaff(Staff staffRecord, Users userRecord){

        System.out.println("Insert Staff Record: "+staffRecord.toString());

        Long usersId = IDGenerator.userIdGenerator();
        String tokenCode = IDGenerator.tokenCode();

        boolean checkReturnU =  usersInfo(userRecord, usersId, tokenCode);boolean checkReturnS = staffInfo(staffRecord, usersId);
            if(!(checkReturnU && checkReturnS)){
                 checkResult = true;
            }
        return checkResult;
    }

    public boolean updateBiography(Staff staffRecord){
        if(staffRecord!= null){
            staffDaoQuery.upload("{_id: "+staffRecord._id+"}",staffRecord);
        }
        return checkResult;
    }


    public boolean uploadStaffInfo(Staff staffRecord){
        staff = staffRecord;
        //user = staffRecord.user;

            userDaoQuery.update("{schoolId: "+Auth.sessionSchoolId()+"}",user);
            //staffDaoQuery.upload("{schoolId: "+Auth.schoolId()+"}",staff);
        return checkResult = true;
    }


    public boolean subDeleteStaffById(long id){
        if(!(id <=0)){
            user._id = id;
            user.status = Status.Deleted.getValue();
            user.modifier = Auth.sessionUsersId();
            user.lastModified = new Date();
            checkResult =  userDaoQuery.update("{schoolId: '"+Auth.sessionSchoolId()+"' , _id: "+id+"}",user);
        }

        return checkResult;
    }

    public StaffRecord findStaffById(long id){
        StaffRecord staffRecord = new StaffRecord();
        try{
            staffRecord.users = userDaoQuery.find("{_id: "+id+"}");
            staffRecord.staffs = staffDaoQuery.find("{_id: "+id+"}");
            System.out.println(staffRecord.toString());
        }catch (Exception e){
            e.getMessage();
            return new StaffRecord();
        }
        return staffRecord;
    }

    private boolean usersInfo(Users users,long staffId ,String tokenCode){
        if(users != null && staffId != 0 && tokenCode != null){
            user = users;

            if(user._id <= 0){
                //  new staff
                user._id = staffId;
                user.password = tokenCode;
                user.tokenCode = tokenCode;
                user.schoolId = Auth.sessionSchoolId();
                user.schoolBranch = Auth.getSessionSchoolBranch();
                user.status = Status.Pending.getValue();
                //users.privilege = "All"; // this part will be auto config by the app
                user.modifier = Auth.sessionUsersId();
                user.modifierDate = DateFormat.getFormatDateAndTime();
                user.lastModified = DateFormat.getFormatDateAndTime();
                userDaoQuery.insert(user);
                System.out.println("Insert Users Info: "+user.toString());
                checkResult = true;

            }else{
                // update staff details
                //users.id = staffRecord.user.id;
                user.modifierDate = DateFormat.getFormatDateAndTime();
                userDaoQuery.update("{_id: "+user._id+"}",user);
                checkResult = true;
            }

        }
       return checkResult;
    }

    private boolean staffInfo(Staff staffRecord,long staffId){

        if(staffRecord != null && staffId != 0){
            staff = staffRecord;
            System.out.println(Auth.sessionSchoolId()+" :SCHID "+Auth.sessionUsersId()+" :USERID");
            staff.modifierDate = DateFormat.getFormatDateAndMounth();
            staff.lastModified = DateFormat.getFormatDateAndMounth();
            staff.schoolId = Auth.sessionSchoolId();
            staff.modifier = Auth.sessionUsersId();
            System.out.println("SchId: "+staff.schoolId+" ModId: "+staff.modifier);

            if(staffRecord._id <= 0){
                staff._id = staffId;
                staffDaoQuery.insert(staff);
                System.out.println("Insert Staff Info: "+staff.toString());
            }else{
                //staff.id = staffRecord.staffs.id;
                staffDaoQuery.upload("{_id: "+staff._id+"}",staff);
            }

            checkResult = true;

        }

        return checkResult;
    }

    public List<StaffUsers> findAllStaff(){
        String schId = Auth.sessionSchoolId();
        // use aggregate in this part
        List<StaffUsers> staffUsersList;
        System.out.println("Session SchId: "+Auth.sessionSchoolId() );
        List<Staff> staffs = staffDaoQuery.findAllById("{schoolId: '"+Auth.sessionSchoolId()+"'}");
        List<Users> userses = userDaoQuery.listAllById("{schoolId: '"+Auth.sessionSchoolId()+"'}");
        System.out.println("Session Staff SchId: "+staffs.iterator().next()._id );
        System.out.println("Session Users SchId: "+userses.iterator().next()._id );
        if(staffs.size() != 0 && userses.size() != 0){
            staffUsersList  = staffUsers.convertStaffData(userses,staffs,
                    schConView.getAllClassRoom(schId),
                    schConView.getAllSubject(schId),
                    schConView.getAllDepartment(schId));
            return staffUsersList;
        }
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "StaffRecord{" +
                "staffs=" + staffs +
                ", users=" + users +
                '}';
    }
}
