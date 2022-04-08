package controllers;

import api.auth.Auth;
import com.fasterxml.jackson.databind.node.ObjectNode;
import data_services.AppTraffic;
import data_services.sch_config.SchConfigServices;
import models.LoginSession;
import models.UserCookies;
import models.education.config.Grade;
import org.jetbrains.annotations.NotNull;
import play.libs.Json;
import play.mvc.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Ibrahim Olanrewaju S on 20/12/2016.
 */
public class QusiController extends Controller {
    private AppTraffic appTraffic;

    @Inject
    private QusiController(AppTraffic appTraffic){
        this.appTraffic = appTraffic;
    }

    public QusiController(){}

    private static final String SESSION_KEY = "authUser";

    public static Boolean auth = false;

//    public static Map<String, Object> project = play.Configuration.root().asMap();

    public static ObjectNode responseJson = Json.newObject().removeAll();

    public static final String RESPONSE_CODE = "responseCode";

    public static final String RESPONSE_DESC = "responseDescription";

    public static final String RESPONSE_RECORD = "Records";

    public static final int SUCCESS = 200;

    public static final int FAILED = 503;

   // public static  String userAgent = request().getHeader(Http.HeaderNames.USER_AGENT);

//    public static  String ipAddress = request().getHeader("X-Real-IP");

//    public static  String callingUrl = request().getHeader(Http.HeaderNames.REFERER);

   // public static  play.Configuration playConfig = play.Configuration.root();

    public void cookies(){
        UserCookies userCookies = new UserCookies();
        userCookies._id = Auth.sessionUsersId();
        userCookies.acceptLanguage = request().acceptLanguages().iterator().next().language();
        userCookies.device = request().cookies().iterator().next().name();
        userCookies.deviceName = request().body().asText();
        userCookies.deviceBrowserType = request().username();
        userCookies.domain = request().uri().trim();
        userCookies.geoAddress = request().cookies().iterator().next().domain();
        userCookies.deviceOs = request().cookies().iterator().next().path();
        userCookies.ipAddress = request().host();
        userCookies.userAgent = request().remoteAddress();
        userCookies.subDomain = request().method();
        System.out.println(userCookies.toString());
        //appTraffic.insertUserCookies(userCookies);
    }

    public void createLogSession(long id,String role,String onlineStatus){
        LoginSession loginSession = new LoginSession();
        loginSession._id = id;
        loginSession.schoolId = Auth.sessionSchoolId();
        loginSession.role = role;
        loginSession.onlineStatus = onlineStatus;
        loginSession.modifier = Auth.sessionUsersId();
        loginSession.modified = new Date();
        loginSession.lastModified = new Date();
       // appTraffic.insertLoginSession(loginSession);
    }

  /*  public void gatewaySession(String onlineStatus){
        appTraffic.updateSession(onlineStatus);
    }*/
    //work on this latter
   /* public LoginSession sessionCheckPoint(String schoolId, long userId){
      return   appTraffic.findSession(schoolId,userId);
    }*/

    @NotNull
    public Result GO_HOME() {
        return redirect("/");
    }

    @NotNull
    public Result PAGE_404() {
        return ok(views.html.errors.page_404.render());
        //return ok();
    }


//    protected static final String userId = session().get("user");

   /* public Result REDIRECT_BACK() {
        if (callingUrl == null) {
            return redirect("/");
        }
        return redirect(callingUrl);
    }*/
}
