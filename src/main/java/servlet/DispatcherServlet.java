package servlet;

import dao.DefaultTransactionManager;
import dao.JdbcImpl.ActivityRequestSqlDao;
import dao.JdbcImpl.ActivitySqlDao;
import dao.JdbcImpl.ActivityTypeSqlDao;
import dao.JdbcImpl.UserSqlDao;
import dao.TransactionManager;
import dao.dbcp.ConnectionManager;
import dao.dbcp.MySqlConnectionManager;
import dao.interfaces.ActivityDao;
import dao.interfaces.ActivityRequestDao;
import dao.interfaces.ActivityTypeDao;
import dao.interfaces.UserDao;
import factory.CommandFactory;
import services.impl.ActivityRequestServiceImpl;
import services.impl.ActivityServiceImpl;
import services.impl.ActivityTypeServiceImpl;
import services.impl.UserServiceImp;
import services.interfaces.ActivityRequestService;
import services.interfaces.ActivityService;
import services.interfaces.ActivityTypeService;
import services.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class DispatcherServlet extends HttpServlet {
    private CommandFactory commandFactory;

    @Override
    public void init() {
        ConnectionManager connectionManager = new MySqlConnectionManager();
        TransactionManager transactionManager = new DefaultTransactionManager(connectionManager);

        UserDao userDao = new UserSqlDao(transactionManager);
        UserService userService = new UserServiceImp(userDao, transactionManager);
        ActivityDao activityDao = new ActivitySqlDao(transactionManager);
        ActivityTypeDao activityTypeDao = new ActivityTypeSqlDao(transactionManager);
        ActivityService activityService = new ActivityServiceImpl(activityDao, transactionManager);
        ActivityRequestDao activityRequestDao = new ActivityRequestSqlDao(transactionManager);
        ActivityRequestService activityRequestService = new ActivityRequestServiceImpl(activityRequestDao, transactionManager);
        ActivityTypeService activityTypeService = new ActivityTypeServiceImpl(activityTypeDao, transactionManager);


        commandFactory = new CommandFactory(userService, activityService, activityRequestService, activityTypeService);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            commandFactory.perform(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            commandFactory.perform(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
