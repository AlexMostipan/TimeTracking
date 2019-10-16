package dao.JdbcImpl;


import dao.TransactionManager;
import dao.mappers.Mapper;
import exceptions.InternalException;
import org.apache.log4j.Logger;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSqlDao<T> {
    private static final Logger LOGGER = Logger.getLogger(AbstractSqlDao.class);
    private TransactionManager transactionManager;

    public AbstractSqlDao(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }


    protected int create(String sqlQuery, PreparedStatementConsumer preparedStatementConsumer) {
        Connection connection = transactionManager.getConnection();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatementConsumer.prepare(preparedStatement);
            int result = preparedStatement.executeUpdate();
            ResultSet tableKeys = preparedStatement.getGeneratedKeys();
            if (result > 0 && tableKeys.next()) {
                return tableKeys.getInt(1);
            } else {
                InternalException internalAppException = new InternalException("Can't create data in db");
                LOGGER.error(internalAppException.getMessage());
                throw internalAppException;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new InternalException(e.getMessage());
        }
    }


    protected int count(String sqlQuery) {
        Connection connection = transactionManager.getConnection();
        try (ResultSet resultSet =
                     connection.createStatement().executeQuery(sqlQuery)) {
            return resultSet.next() ? resultSet.getInt(1) : 0;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new InternalException(e.getMessage());
        }
    }


    protected int count(String sqlQuery, PreparedStatementConsumer preparedStatementConsumer) {
        Connection connection = transactionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatementConsumer.prepare(preparedStatement);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    return 0;
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new InternalException(e);
        }
    }

    protected T find(String sql, PreparedStatementConsumer preparedStatementConsumer, Mapper<T> mapper) {
        Connection connection = transactionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatementConsumer.prepare(preparedStatement);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapper.getEntity(resultSet);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new InternalException(e);
        }
    }

    protected List<T> findAll(String query, Mapper<T> mapper) {
        Connection connection = transactionManager.getConnection();
        List<T> list = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                list.add(mapper.getEntity(resultSet));
            }
            return list;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new InternalException(e);
        }
    }


    protected List<T> findAll(String query, PreparedStatementConsumer preparedStatementConsumer, Mapper<T> mapper) {
        Connection connection = transactionManager.getConnection();
        List<T> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatementConsumer.prepare(preparedStatement);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next())
                    list.add(mapper.getEntity(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new InternalException(e);
        }
        return list;
    }

    protected boolean updateOrDelete(String query, PreparedStatementConsumer preparedStatementConsumer) {
        Connection connection = transactionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatementConsumer.prepare(preparedStatement);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new InternalException(e);
        }
    }
}