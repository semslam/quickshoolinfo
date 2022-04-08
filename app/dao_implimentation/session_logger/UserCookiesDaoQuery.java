package dao_implimentation.session_logger;

import dao_implimentation.DbConnection;
import models.UserCookies;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu on 28/08/2017.
 */
public class UserCookiesDaoQuery {

    private UserCookies userCookies;
    private DbConnection dbConnection;
    private MongoCollection mongoCollection;
    private boolean checkResult = false;

    public UserCookiesDaoQuery(){}

    @Inject
    private UserCookiesDaoQuery(DbConnection dbConnection){
        this.dbConnection = dbConnection;
        this.userCookies = new UserCookies();
        this.mongoCollection = dbConnection.getConnection("logger");
    }

    public boolean insert(UserCookies userCookies){
        if(userCookies != null){
            mongoCollection.insert(userCookies);
            checkResult = true;
        }
        return checkResult;
    }

    public UserCookies find(String query){
        return mongoCollection.findOne(query).as(UserCookies.class);
    }

    public List<UserCookies> findAllById(String query){
        List<UserCookies> list = new ArrayList<>();
        synchronized(mongoCollection) {
            MongoCursor<UserCookies> userCookies = mongoCollection.find(query).as(UserCookies.class);
            int count = userCookies.count();
            if (count != 0) {
                for (UserCookies logger : userCookies) {
                    list.add(logger);
                }
            }else {
                return new ArrayList<>();
            }
        }
        return list;
    }

    public boolean upload(String query,UserCookies userCookies){
        if(query != null && userCookies != null){
            mongoCollection.update(query).with(userCookies);
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
}
