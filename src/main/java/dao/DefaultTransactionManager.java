package dao;

import exceptions.InternalException;
import dao.dbcp.ConnectionManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class DefaultTransactionManager implements TransactionManager {
    private static final Logger LOGGER = Logger.getLogger(DefaultTransactionManager.class);

    private ThreadLocal<Connection> threadConnection = new ThreadLocal<>();
    private ConnectionManager connectionManager;

    public DefaultTransactionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Connection getConnection() {
        createConnection();
        return threadConnection.get();
    }


    @Override
    public void closeConnection() {
        try {
            Connection connection = threadConnection.get();
            if (connection != null) {
                connection.close();
            }
            threadConnection.remove();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }


    @Override
    public void startTransaction() {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            connection.setSavepoint();
        } catch (SQLException e) {
            InternalException exception = new InternalException("Can't start transaction.");
            LOGGER.error(exception.getMessage());
            throw exception;
        }
    }


    @Override
    public void endTransaction() {
        Connection connection = getConnection();
        if (connection == null) {
            InternalException exception = new InternalException("Can't end transaction. Connection is null.");
            LOGGER.error(exception.getMessage());
            throw exception;
        }
        try {
            connection.commit();
        } catch (SQLException e) {
            InternalException commitException = new InternalException("Can't commit changes to db.");
            LOGGER.error("Rolling back.", commitException);
            try {
                connection.rollback();
                LOGGER.info("Commit successfully rolled back");
            } catch (SQLException e1) {
                InternalException rollBackException = new InternalException("Can't rollback changes");
                LOGGER.error(rollBackException.getMessage());
                throw rollBackException;
            }
            throw commitException;
        }
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    private void createConnection() {
        if (threadConnection.get() == null) {
            threadConnection.set(connectionManager.getConnection());
        }
    }
}