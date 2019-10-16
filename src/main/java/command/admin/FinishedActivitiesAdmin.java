package command.admin;

import command.Command;
import model.Activity;
import model.enums.ActivityStatus;
import services.interfaces.ActivityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FinishedActivitiesAdmin implements Command {
    private ActivityService activityService;


    public FinishedActivitiesAdmin(ActivityService activityService) {
        this.activityService = activityService;

    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Activity> activities = activityService.getActivityByStatus(ActivityStatus.FINISHED);
        request.setAttribute("allActivities", activities);
        request.getRequestDispatcher("/pages/admin.jsp").forward(request, response);
    }
}
