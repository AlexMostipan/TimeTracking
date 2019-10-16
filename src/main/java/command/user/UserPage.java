package command.user;

import command.Command;
import model.Activity;
import model.User;
import services.interfaces.ActivityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserPage implements Command {
    private ActivityService activityService;

    public UserPage(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = ((User) request.getSession().getAttribute("user")).getId();
        List<Activity> activities = activityService.getActivityByUserId(userId);
        request.setAttribute("activities", activities);
        request.getRequestDispatcher("/pages/user.jsp").forward(request, response);

    }
}
