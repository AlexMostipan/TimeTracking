package util;

public class UrlRequests {
    public static final String SITE = "/site";

    public static final String HOME_PAGE = "/home_page";

    public static final String LOGIN_PAGE = "/login_page";
    public static final String LOGIN = LOGIN_PAGE + "/login";
    public static final String LOGOUT = "/logout";

    public static final String SIGN_UP_PAGE = "/sign_up_page";
    public static final String SIGN_UP = SIGN_UP_PAGE + "/sign_up";
    public static final String USER_START_PAGE = "/user_page";
    public static final String ADMIN_START_PAGE = "/admin_page";
    public static final String SHOW_REQUEST = "/requests";
    public static final String CREATE_REQUEST = "/create_request";
    public static final String OPEN_CREATE_PAGE = "/create_activity_page";
    public static final String CREATE_ACTIVITY = OPEN_CREATE_PAGE + "/create_activity";
    public static final String DELETE_ACTIVITY = "/delete";
    public static final String FINISH_ACTIVITY = "/finish";
    public static final String FINISHED_ACTIVITIES_USER = "/finished_user";
    public static final String DURING_ACTIVITIES_USER = "/during_user";
    public static final String FINISHED_ACTIVITIES_ADMIN = "/finished_admin";
    public static final String DURING_ACTIVITIES_ADMIN = "/during_admin";

    private UrlRequests() {
    }
}
