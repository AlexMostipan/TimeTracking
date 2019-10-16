package command;


import model.User;
import model.enums.Role;
import services.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login implements Command {
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private Command adminPage;
    private Command userPage;
    private UserService userService;

    public Login(Command adminPage, Command userPage, UserService userService) {
        this.adminPage = adminPage;
        this.userPage = userPage;
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(EMAIL);
        String pass = request.getParameter(PASSWORD);
        User user = userService.login(login, pass);
        if (user == null) {
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("user", user);
            if (user.getRole() == Role.ADMIN) {
                adminPage.execute(request, response);

            } else {
                userPage.execute(request, response);
            }
        }
    }
}
