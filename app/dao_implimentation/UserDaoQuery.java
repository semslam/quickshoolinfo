package dao_implimentation;

import models.Users;

import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju S on 6/28/2016.
 */
public class UserDaoQuery {

    private DbConnection dbConnection;
    private Users users;
    private MongoCollection mongoCollection;
    private boolean checkResult = false;

    @Inject
    public UserDaoQuery(DbConnection  dbConnection) {
        this.dbConnection = dbConnection;
        this.users = new Users();
        this.mongoCollection = dbConnection.getConnection("users");
    }

    public UserDaoQuery(){}

    public List<Users> listAll(){
        List<Users> userses = new ArrayList<>();
        synchronized(mongoCollection) {
            MongoCursor<Users> usersList = mongoCollection.find().as(Users.class);
            int count = usersList.count();
            if (count != 0) {
                for (Users users : usersList) {
                    userses.add(users);
                }
            }
        }
        return userses;
    }

    public List<Users> listAllById(String query){
        List<Users> list = new ArrayList<>();
        synchronized(mongoCollection) {
            // try catch with final
            MongoCursor<Users> usersList = mongoCollection.find(query).as(Users.class);
            int count = usersList.count();
            if (count != 0) {
                for (Users users : usersList) {
                    list.add(users);
                }
            }else {
                return new ArrayList<>();
            }
        }
        return list;
    }

    public Users find(String query){
        return mongoCollection.findOne(query).as(Users.class);
    }


    /*public void insertUser(String query,Users cms){
        mongoCollection.update(query).with(cms);
    }*/

    public boolean insert(Users users){
        if(users != null){
            mongoCollection.insert(users);
            checkResult = true;
        }
       return checkResult;
    }

    public boolean update(String query,Users users){
        if(query != null && users != null){
            mongoCollection.update(query).with(users);
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


    public long count(String query){
       return mongoCollection.count(query);
    }

    public long count(){
        return mongoCollection.count();
    }

    public void aggregation(String query){
        mongoCollection.aggregate(query);
    }
   /* public boolean isSchoolNameExist(String field, String query){
         findOne(field);
        return true;
    }*/
    // query db for mail if it is exist, compare the result with the provide mail, if it return null or not match
   /* public boolean isFieldExist(String query){
        boolean check = false;
        Users us = new Users();// temp object
        try{
            us = findOne(query);
            if(us == null) {
                System.out.println("The Object is Empty");
                check = true;
            }

       }catch(NullPointerException ne){
            ne.printStackTrace();
            check = false;
        }
        return check;
    }

    public boolean isEmailExist( String query){
        isFieldExist("{user_email: '"+query+"'}");
        return true;
    }*/


}
