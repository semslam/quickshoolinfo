package dao_implimentation.session_logger;

import dao_implimentation.DbConnection;
import models.LoginSession;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu on 28/08/2017.
 */
public class LoginSessionDaoQuery {

    private LoginSession loginSession;
    private DbConnection dbConnection;
    private MongoCollection mongoCollection;
    private boolean checkResult = false;

    public LoginSessionDaoQuery(){}

    @Inject
    private LoginSessionDaoQuery(DbConnection dbConnection){
        this.dbConnection = dbConnection;
        this.loginSession = new LoginSession();
        this.mongoCollection = dbConnection.getConnection("login_session");
    }

    public boolean insert(LoginSession loginSession){
        if(loginSession != null){
            mongoCollection.insert(loginSession);
            checkResult = true;
        }
        return checkResult;
    }

    public LoginSession find(String query){
        return mongoCollection.findOne(query).as(LoginSession.class);
    }

    public List<LoginSession> findAllById(String query){
        List<LoginSession> list = new ArrayList<>();
        synchronized(mongoCollection) {
            MongoCursor<LoginSession> loginSessions = mongoCollection.find(query).as(LoginSession.class);
            int count = loginSessions.count();
            if (count != 0) {
                for (LoginSession loginSession : loginSessions) {
                    list.add(loginSession);
                }
            }else {
                return new ArrayList<>();
            }
        }
        return list;
    }

    public boolean upload(String query,LoginSession loginSession){
        if(query != null && loginSession != null){
            mongoCollection.update(query).with(loginSession);
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
