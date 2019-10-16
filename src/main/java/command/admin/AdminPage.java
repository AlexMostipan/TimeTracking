package command.admin;

import command.Command;
import model.Activity;
import services.interfaces.ActivityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminPage implements Command {

    private ActivityService activityService;

    public AdminPage(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Activity> allActivities = activityService.getAllActivities();
        request.setAttribute("allActivities", allActivities);
        request.getRequestDispatcher("/pages/admin.jsp").forward(request, response);
    }
}
