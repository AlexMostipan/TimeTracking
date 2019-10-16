package command;

import model.User;
import services.interfaces.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class SignUp implements Command {
    private UserService userService;


    public SignUp(UserService userService) {
        this.userService = userService;

    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");

        User userFromFront = userService.getUserByUsername(username);

        if (userFromFront == null) {
            userService.create(username, email, pass);
        } else {
            out.println("User" + userFromFront.getUsername() +
                    "already exists");
        }

    }
}
