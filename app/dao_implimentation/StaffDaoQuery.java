
package dao_implimentation;

import models.staff_records.Staff;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ibrahim Olanrewaju S on 07/09/2016.
 */

public class StaffDaoQuery {

    private Staff staff;
    private DbConnection dbConnection;
    private MongoCollection mongoCollection;
    private boolean checkResult = false;

    public StaffDaoQuery(){}

    @Inject
    private StaffDaoQuery(DbConnection dbConnection){
        this.dbConnection = dbConnection;
        this.staff = new Staff();
        this.mongoCollection = dbConnection.getConnection("staff");
    }

    public boolean insert(Staff staff){
        if(staff != null){
            mongoCollection.insert(staff);
            checkResult = true;
        }
       return checkResult;
    }

    public Staff find(String query){
        return mongoCollection.findOne(query).as(Staff.class);
    }

    public List<Staff> findAllById(String query){
        List<Staff> list = new ArrayList<>();
        synchronized(mongoCollection) {
            MongoCursor<Staff> staffs = mongoCollection.find(query).as(Staff.class);
            int count = staffs.count();
            if (count != 0) {
                for (Staff staff : staffs) {
                    list.add(staff);
                }
            }else {
                return new ArrayList<>();
            }
        }
        return list;
    }

    public boolean upload(String query,Staff staff){
        if(query != null && staff != null){
            mongoCollection.update(query).with(staff);
            checkResult = true;
        }
        return checkResult;
    }

    public boolean delete(String query){
        if(query != null){
            mongoCollection.remove(query);
            checkResult = true;
        }
       return checkResult;
    }

    // return true if the object is not exist
    public boolean isStaffExist(String query){
        boolean check;
        Staff staff1;
        try{
            staff1 = find(query);
            if(staff1 == null)
                System.out.println("The Object is Empty");
            check = true;

        }catch(NullPointerException ne){
            check =false;
            ne.printStackTrace();
        }
        return check;
    }




}

