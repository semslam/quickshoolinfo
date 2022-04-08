package api.auth;

import models.Users;
import play.mvc.Http;

/**
 * |
 * | Created by Ibrahim Olanrewaju Abdulsemu
 * | On 18/12/2016 3:15 AM
 * |
 **/
public class Auth {

    private static final String SESSION_NAME = "user";
    private static final String SESSION_ROLE = "role";
    private static final String SESSION_LEVEL = "role_level";
    private static final String SESSION_ID = "id";
    private static final String SESSION_CONNECT = "connected";
    private static final String SESSION_SCHOOL_ID = "school_id";
    private static final String SESSION_PLAN = "app_plane_package";
    private static final String SESSION_EMAIL = "email";
    private static final String SESSION_STATUS = "status";
    private static  final String SESSION_SCHOOL_BRANCH = "school_branch";

    private static final String ROLE_LEVEL_AGENT = "2";
    private static final String ROLE_LEVEL_CUSTOMER = "3";

    /**
     * Returns true if the user is not anonymous
     *
     * @return
     */
    public static boolean isAuthenticated() {
        if (Http.Context.current().session().get(SESSION_CONNECT) != null) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if the user is not an anonymous or a remember-me user
     *
     * @return
     */
    public static boolean isFullyAuthenticated() {
        if (Http.Context.current().session().get(SESSION_NAME) != null) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if the user is not an anonymous or a remember-me user connected
     *
     * @return
     */
    public static boolean isConnected() {
        if (Http.Context.current().session().get(SESSION_CONNECT) != null) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if the current principal is a remember-me user isAuthenticated()
     *
     * @return
     */
    public static boolean isRememberMe() {
        return false;
    }

    /**
     * Returns true if the current principal is an anonymous user
     *
     * @return
     */
    public static boolean isAnonymous() {
        if (Http.Context.current().session().get(SESSION_NAME) == null) {
            return true;
        }
        return false;
    }

    private static String role() {
        return Http.Context.current().session().get(SESSION_ROLE);
    }

    private static String schoolId() {
        return Http.Context.current().session().get(SESSION_SCHOOL_ID);
    }

    private static String usersEmail(){return Http.Context.current().session().get(SESSION_EMAIL);}
    private static String schoolBranchID(){return Http.Context.current().session().get(SESSION_SCHOOL_BRANCH);}

    private static Long userId() {
        return Long.parseLong(Http.Context.current().session().get(SESSION_ID));
    }

    public static Users user() {
        if (isAuthenticated()) {
            //return Users.find.byId(userId());
        }
        return null;
    }

    public static Long sessionUsersId(){
        if (isAuthenticated()) {
         return  userId();
        }
        return null;
    }

    public static String sessionSchoolId(){
        if (isAuthenticated()) {
            return  schoolId();
        }
        return null;
    }

    public static String getSessionSchoolBranch(){
        if (isAuthenticated()) {
            return  schoolBranchID();
        }
        return null;
    }

    public static String getSessionRole(){
        if (isAuthenticated()) {
            return  role();
        }
        return null;
    }

    public static String sessionEmail(){
        if (isAuthenticated()){
            return usersEmail();
        }
        return null;
    }

    public static int getSessionLevel() {
        if (Http.Context.current().session().get(SESSION_LEVEL) != null)
            return Integer.parseInt(Http.Context.current().session().get(SESSION_LEVEL));
        return 0; //invalid session level.
    }
}
