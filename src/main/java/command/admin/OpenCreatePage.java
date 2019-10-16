package command.admin;

import command.Command;
import model.ActivityType;
import model.User;
import services.interfaces.ActivityTypeService;
import services.interfaces.UserService;
import util.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OpenCreatePage implements Command {
    private UserService userService;
    private ActivityTypeService activityTypeService;

    public OpenCreatePage(UserService userService, ActivityTypeService activityTypeService) {
        this.userService = userService;
        this.activityTypeService = activityTypeService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        List<User> allUsers = userService.getAllUsers();
        request.setAttribute("allUsers", allUsers);
        List<ActivityType> allActivityTypes = activityTypeService.getAllActivityTypes();
        request.setAttribute("allActivityTypes", allActivityTypes);
        request.getRequestDispatcher(PagesPath.CREATE_PAPE).forward(request, response);
    }
}

