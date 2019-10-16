package factory;

import command.Command;
import command.Login;
import command.Logout;
import command.SignUp;
import command.admin.*;
import command.user.*;
import services.interfaces.ActivityRequestService;
import services.interfaces.ActivityService;
import services.interfaces.ActivityTypeService;
import services.interfaces.UserService;
import util.UrlRequests;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<String, Command> commandMap = new HashMap<>();


    public CommandFactory(UserService userService, ActivityService activityService, ActivityRequestService activityRequestService, ActivityTypeService activityTypeService) {
        AdminPage adminPage = new AdminPage(activityService);
        UserPage userPage = new UserPage(activityService);
        OpenLoginPage openLoginPage = new OpenLoginPage();
        commandMap.put(UrlRequests.USER_START_PAGE, new UserPage(activityService));
        commandMap.put(UrlRequests.ADMIN_START_PAGE, new AdminPage(activityService));
        commandMap.put(UrlRequests.LOGIN_PAGE, new OpenLoginPage());
        commandMap.put(UrlRequests.LOGIN, new Login(adminPage, userPage, userService));
        commandMap.put(UrlRequests.OPEN_CREATE_PAGE, new OpenCreatePage(userService, activityTypeService));
        commandMap.put(UrlRequests.LOGOUT, new Logout());
        commandMap.put(UrlRequests.CREATE_ACTIVITY, new CreateActivity(userService, activityService, activityTypeService));
        commandMap.put(UrlRequests.SIGN_UP_PAGE, new OpenSingUpPage());
        commandMap.put(UrlRequests.SIGN_UP, new SignUp(userService));
        commandMap.put(UrlRequests.SHOW_REQUEST, new ShowAllRequest(activityRequestService));
        commandMap.put(UrlRequests.CREATE_REQUEST, new CreateRequest(activityRequestService, userPage));
        commandMap.put(UrlRequests.DELETE_ACTIVITY, new DeleteActivity(activityService, adminPage));
        commandMap.put(UrlRequests.FINISH_ACTIVITY, new FinishActivity(activityService, userPage));
        commandMap.put(UrlRequests.FINISHED_ACTIVITIES_USER, new FinishedActivitiesUser(activityService));
        commandMap.put(UrlRequests.DURING_ACTIVITIES_USER, new DuringActivitiesUser(activityService));
        commandMap.put(UrlRequests.DURING_ACTIVITIES_ADMIN, new DuringActivitiesAdmin(activityService));
        commandMap.put(UrlRequests.FINISHED_ACTIVITIES_ADMIN, new FinishedActivitiesAdmin(activityService));
    }

    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String requestURI = request.getRequestURI();
        String uri = requestURI.substring(requestURI.lastIndexOf(UrlRequests.SITE) + UrlRequests.SITE.length());
        Command command = commandMap.get(uri);
        if (command == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            command.execute(request, response);
        }
    }
}
