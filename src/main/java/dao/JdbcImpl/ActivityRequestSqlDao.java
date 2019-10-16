package dao.JdbcImpl;

import dao.TransactionManager;
import dao.interfaces.ActivityRequestDao;

import dao.mappers.ActivityRequestMapper;
import model.ActivityRequest;
import model.enums.RequestType;

import java.util.List;

public class ActivityRequestSqlDao extends AbstractSqlDao<ActivityRequest> implements ActivityRequestDao {
    public ActivityRequestSqlDao(TransactionManager transactionManager) {
        super(transactionManager);
    }

    public static final String ACTIVITY_REQUEST_TABLE = "activity_request";

    public static final String ACTIVITY_REQUEST_COLUMN_ID = "activity_request_id";
    public static final String ACTIVITY_REQUEST_COLUMN_USER_ID = "user_id";
    public static final String ACTIVITY_REQUEST_COLUMN_REQUEST_TYPE = "request_type";
    public static final String ACTIVITY_REQUEST_COLUMN_CREATION_DATE = "creationDate";
    private static final String DELETE = "DELETE FROM " + ACTIVITY_REQUEST_TABLE + " WHERE " + ACTIVITY_REQUEST_COLUMN_ID + " = ?";
    private static final String SELECT_ALL = "SELECT * FROM activity_request  LEFT JOIN user  ON activity_request.user_id = user.user_id;";
    private static final String SELECT_ACTIVITY_BY_USER_ID = "SELECT * FROM " + ACTIVITY_REQUEST_TABLE + " WHERE " + ACTIVITY_REQUEST_COLUMN_USER_ID + " = ?";
    private static final String SELECT_ACTIVITY_BY_REQUEST_TYPE = "SELECT * FROM " + ACTIVITY_REQUEST_TABLE + " WHERE " + ACTIVITY_REQUEST_COLUMN_REQUEST_TYPE + " = ?";


    private static final String INSERT = "INSERT INTO " + ACTIVITY_REQUEST_TABLE + " ("
            + ACTIVITY_REQUEST_COLUMN_ID + ", "
            + ACTIVITY_REQUEST_COLUMN_USER_ID + ", "
            + ACTIVITY_REQUEST_COLUMN_REQUEST_TYPE + ", "
            + ACTIVITY_REQUEST_COLUMN_CREATION_DATE + ") "
            + "VALUES (?, ?, ? , ?) ";
    public static final String UPDATE = "UPDATE " + ACTIVITY_REQUEST_TABLE + " SET "
            + ACTIVITY_REQUEST_COLUMN_USER_ID + " = ?, "
            + ACTIVITY_REQUEST_COLUMN_REQUEST_TYPE + " = ?, "
            + "WHERE + " + ACTIVITY_REQUEST_COLUMN_ID + " = ?";

    @Override
    public int create(ActivityRequest activityRequest) {
        return create(INSERT, preparedStatement -> {
            preparedStatement.setInt(1, activityRequest.getId());
            preparedStatement.setInt(2, activityRequest.getUser().getId());
            preparedStatement.setString(3, activityRequest.getRequestType().toString());
            preparedStatement.setString(4, activityRequest.getCreationDate());
        });
    }

    @Override
    public boolean update(ActivityRequest activityRequest) {
        return updateOrDelete(UPDATE, preparedStatement -> {
            preparedStatement.setInt(1, activityRequest.getUser().getId());
            preparedStatement.setString(2, activityRequest.getRequestType().toString());
            preparedStatement.setInt(3, activityRequest.getId());

        });
    }

    @Override
    public List<ActivityRequest> findAll() {
        return findAll(SELECT_ALL, new ActivityRequestMapper());
    }

    @Override
    public boolean delete(int id) {
        return updateOrDelete(DELETE, preparedStatement -> preparedStatement.setInt(1, id));
    }

    @Override
    public List<ActivityRequest> findByUserId(int userId) {
        return findAll(SELECT_ACTIVITY_BY_USER_ID, preparedStatement -> preparedStatement.setInt(1, userId), new ActivityRequestMapper());
    }

    @Override
    public List<ActivityRequest> findByType(RequestType requestType) {
        return findAll(SELECT_ACTIVITY_BY_REQUEST_TYPE, preparedStatement -> preparedStatement.setString(1, requestType.toString()), new ActivityRequestMapper());
    }
}
