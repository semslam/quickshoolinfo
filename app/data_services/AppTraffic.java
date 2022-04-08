package data_services;

import api.auth.Auth;
import dao_implimentation.session_logger.LoggerDaoQuery;
import dao_implimentation.session_logger.LoginSessionDaoQuery;
import dao_implimentation.session_logger.UserCookiesDaoQuery;
import models.Logger;
import models.LoginSession;
import models.UserCookies;

import javax.inject.Inject;
import java.util.Date;

/**
 * Created by TRAVELDEN DEV on 28/08/2017.
 */
public class AppTraffic {

    private LoggerDaoQuery loggerDaoQuery;
    private Logger logger;
    private UserCookiesDaoQuery userCookiesDaoQuery;
    private UserCookies userCookies;
    private LoginSessionDaoQuery loginSessionDaoQuery;
    private LoginSession loginSession;
    public boolean checkResult = false;
    @Inject
    public AppTraffic (LoggerDaoQuery loggerDaoQuery,
                       UserCookiesDaoQuery userCookiesDaoQuery,
                       LoginSessionDaoQuery loginSessionDaoQuery){
        this.loggerDaoQuery = loggerDaoQuery;
        this.userCookiesDaoQuery= userCookiesDaoQuery;
        this.loginSessionDaoQuery = loginSessionDaoQuery;
        this.logger = new Logger();
        this.userCookies = new UserCookies();
        this.loginSession = new LoginSession();
    }

    private AppTraffic(){}

    public boolean insertLogger(Logger logger){
        if(logger != null)
            checkResult =  loggerDaoQuery.insert(logger);
            return checkResult = true;
    }

    public boolean insertUserCookies(UserCookies userCookies){
        if(logger != null)
            checkResult =  userCookiesDaoQuery.insert(userCookies);
        return checkResult = true;
    }
    public boolean insertLoginSession(LoginSession loginSession){
        if(logger != null)
            checkResult =  loginSessionDaoQuery.insert(loginSession);
        return checkResult = true;
    }

    public boolean updateSession(String query){
        if (query != null)
            loginSession.onlineStatus = query;
            loginSession.role = Auth.getSessionRole();
            loginSession.lastModified = new Date();
            checkResult = loginSessionDaoQuery.upload("{_id: "+ Auth.sessionUsersId()+"}",loginSession);
        return checkResult = true;
    }

    public LoginSession findSession(String schoolId, long id){
        return  loginSessionDaoQuery.find("{ schoolId: "+schoolId+" , _id:"+id+"}");
    }


}
