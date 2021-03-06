package dao.dbcp;

import exceptions.InitialisationException;
import exceptions.InternalException;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;


import java.sql.Connection;
import java.sql.SQLException;

import static util.PropertiesUtil.getProperty;


public class MySqlConnectionManager implements ConnectionManager {
    private static final Logger LOGGER = Logger.getLogger(MySqlConnectionManager.class);
    private static final String PROP_FILE_NAME = "dbcp";

    private String url;
    private String username;
    private String pass;
    private int initialSize;
    private int maxSize;

    private static BasicDataSource instance;

    public MySqlConnectionManager() {
        try {
            url = getProperty("url", PROP_FILE_NAME);
            username = getProperty("username", PROP_FILE_NAME);
            pass = getProperty("pass", PROP_FILE_NAME);
            initialSize = Integer.parseInt(getProperty("initial", PROP_FILE_NAME));
            maxSize = Integer.parseInt(getProperty("max", PROP_FILE_NAME));
            instance = setupDriver();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new InitialisationException("Can't start DBCP");
        }
    }


    public synchronized Connection getConnection() {
        try {
            return instance.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Can't provide connection from pool.");
            throw new InternalException(e);
        }
    }


    private BasicDataSource setupDriver() throws Exception {
        BasicDataSource dbcp = new BasicDataSource();
        dbcp.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dbcp.setUrl(url);
        dbcp.setUsername(username);
        dbcp.setPassword(pass);
        dbcp.setInitialSize(initialSize);
        dbcp.setMaxTotal(maxSize);
        dbcp.getConnection();
        return dbcp;
    }
}