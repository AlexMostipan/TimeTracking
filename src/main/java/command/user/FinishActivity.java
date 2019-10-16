package command.user;

import command.Command;
import services.interfaces.ActivityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FinishActivity implements Command {
    private ActivityService activityService;
    private Command userPage;

    public FinishActivity(ActivityService activityService, Command userPage) {
        this.activityService = activityService;
        this.userPage = userPage;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int activityId = Integer.parseInt(request.getParameter("activityId"));
        activityService.finishActivity(activityId);
        userPage.execute(request, response);
    }
}
