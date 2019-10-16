package command.admin;

import command.Command;
import model.ActivityRequest;
import services.interfaces.ActivityRequestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowAllRequest implements Command {
    private ActivityRequestService activityRequestService;

    public ShowAllRequest(ActivityRequestService activityRequestService) {
        this.activityRequestService = activityRequestService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ActivityRequest> allRequest = activityRequestService.getAllRequest();
        request.setAttribute("allRequest", allRequest);
        request.getRequestDispatcher("/pages/requests.jsp").forward(request, response);
    }
}