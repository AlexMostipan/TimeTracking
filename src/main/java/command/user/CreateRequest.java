package command.user;

import command.Command;
import model.User;
import model.enums.RequestType;
import services.interfaces.ActivityRequestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateRequest implements Command {
    private ActivityRequestService activityRequestService;
    private Command userPage;

    public CreateRequest(ActivityRequestService activityRequestService, Command userPage) {
        this.activityRequestService = activityRequestService;
        this.userPage = userPage;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        activityRequestService.create(user, RequestType.GETTING);
        userPage.execute(request, response);
    }
}
