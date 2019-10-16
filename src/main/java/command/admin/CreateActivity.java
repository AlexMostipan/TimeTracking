package command.admin;

import command.Command;


import model.ActivityType;
import model.User;

import services.interfaces.ActivityService;
import services.interfaces.ActivityTypeService;
import services.interfaces.UserService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CreateActivity implements Command {
    private UserService userService;
    private ActivityService activityService;
    private ActivityTypeService activityTypeService;

    public CreateActivity(UserService userService, ActivityService activityService, ActivityTypeService activityTypeService) {
        this.userService = userService;
        this.activityService = activityService;
        this.activityTypeService = activityTypeService;

    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        User user = userService.getUserByUsername(username);
        int id = Integer.parseInt(request.getParameter("title"));
        String discription = request.getParameter("description");

        ActivityType activityType = activityTypeService.findById(id);
        activityService.create(user, activityType, discription);
        response.sendRedirect(request.getContextPath() + "/site/admin_page");

    }
}