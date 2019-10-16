package filter;

import command.Command;
import command.user.OpenLoginPage;
import model.User;
import model.enums.Role;
import util.UrlRequests;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserAccessFilter implements Filter {
    private static final String[] RESTRICTED_FOR_UNREGISTERED = {
            UrlRequests.ADMIN_START_PAGE,
            UrlRequests.USER_START_PAGE,
            UrlRequests.SHOW_REQUEST,
            UrlRequests.OPEN_CREATE_PAGE,
            UrlRequests.CREATE_REQUEST,
            UrlRequests.CREATE_ACTIVITY,
            UrlRequests.DELETE_ACTIVITY};
    private static final String[] RESTRICTED_FOR_USER = {
            UrlRequests.ADMIN_START_PAGE,
            UrlRequests.SHOW_REQUEST,
            UrlRequests.OPEN_CREATE_PAGE,
            UrlRequests.CREATE_ACTIVITY,
            UrlRequests.DELETE_ACTIVITY};
    private static final String[] RESTRICTED_FOR_ADMIN = {
            UrlRequests.USER_START_PAGE,
            UrlRequests.CREATE_REQUEST};

    private static Command openLoginPage = new OpenLoginPage();

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestPath = request.getRequestURI();
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            for (String restrictedUrl : RESTRICTED_FOR_UNREGISTERED) {
                if (requestPath.contains(restrictedUrl)) {
                    openLoginPage.execute(request, response);
                }
            }
        } else if (!userPermittedToPerformRequest(user, requestPath)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean userPermittedToPerformRequest(User user, String url) {
        Role userRole = user.getRole();
        if (url.contains(UrlRequests.ADMIN_START_PAGE)) {
            return userRole == Role.ADMIN;
        } else if (url.contains(UrlRequests.USER_START_PAGE)) {
            return userRole == Role.USER;
        }
        if (user.getRole() == Role.ADMIN) {
            for (String restricted : RESTRICTED_FOR_ADMIN) {
                if (url.contains(restricted)) {
                    return false;
                }
            }
        } else if (user.getRole() == Role.USER) {
            for (String restricted : RESTRICTED_FOR_USER) {
                if (url.contains(restricted)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void destroy() {
    }
}