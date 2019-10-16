package command.admin;

import command.Command;
import services.interfaces.ActivityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteActivity implements Command {
    private ActivityService activityService;
    private Command adminPage;

    public DeleteActivity(ActivityService activityService, Command adminPage) {
        this.activityService = activityService;
        this.adminPage = adminPage;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int activityId = Integer.parseInt(request.getParameter("activityId"));
        activityService.delete(activityId);
        adminPage.execute(request, response);
    }
}
