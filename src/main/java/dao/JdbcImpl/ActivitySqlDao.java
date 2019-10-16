package dao.JdbcImpl;

import dao.TransactionManager;
import dao.interfaces.ActivityDao;
import dao.mappers.ActivityMapper;
import model.Activity;
import model.enums.ActivityStatus;

import java.util.List;


public class ActivitySqlDao extends AbstractSqlDao<Activity> implements ActivityDao {
    public ActivitySqlDao(TransactionManager transactionManager) {
        super(transactionManager);
    }

    public static final String ACTIVITY_TABLE = "activity";

    public static final String ACTIVITY_COLUMN_ID = "activity_id";
    public static final String ACTIVITY_COLUMN_USER_ID = "user_id";
    public static final String ACTIVITY_COLUMN_DESCRIPTION = "description";
    public static final String ACTIVITY_COLUMN_ACTIVITY_TYPE_ID = "activity_type_id";
    public static final String ACTIVITY_COLUMN_STATUS = "status";
    public static final String ACTIVITY_COLUMN_DATE_START = "dateStart";
    public static final String ACTIVITY_COLUMN_DATE_FINISH = "dateFinish";


    private static final String DELETE = "DELETE FROM " + ACTIVITY_TABLE + " WHERE " + ACTIVITY_COLUMN_ID + " = ?";
    private static final String SELECT_ALL = "SELECT * FROM activity  LEFT JOIN user  ON activity.user_id = user.user_id JOIN activity_type on activity.activity_type_id= activity_type.activity_type_id ;";
    private static final String SELECT_ACTIVITY_BY_USER_ID = "SELECT * FROM activity  LEFT JOIN user  ON activity.user_id = user.user_id JOIN activity_type on activity.activity_type_id= activity_type.activity_type_id where activity.user_id  =  ?";
    private static final String SELECT_ACTIVITY_BY_STATUS = "SELECT * FROM activity  LEFT JOIN user  ON activity.user_id = user.user_id JOIN activity_type on activity.activity_type_id= activity_type.activity_type_id WHERE activity.status = ?";
    private static final String SELECT_ACTIVITY_BY_STATUS_AND_BY_USER_ID = "SELECT * FROM activity  LEFT JOIN user  ON activity.user_id = user.user_id JOIN activity_type on activity.activity_type_id= activity_type.activity_type_id WHERE activity.status = ? AND activity.user_id = ?";
    private static final String INSERT = "INSERT INTO " + ACTIVITY_TABLE + " ("
            + ACTIVITY_COLUMN_USER_ID + ", "
            + ACTIVITY_COLUMN_DESCRIPTION + ", "
            + ACTIVITY_COLUMN_DATE_START + ", "
            + ACTIVITY_COLUMN_ACTIVITY_TYPE_ID + ", "
            + ACTIVITY_COLUMN_STATUS + ") "
            + "VALUES ( ?, ?, ? , ? , ?)  ";
    public static final String UPDATE = "UPDATE " + ACTIVITY_TABLE + " SET "
            + ACTIVITY_COLUMN_USER_ID + " = ?, "
            + ACTIVITY_COLUMN_DATE_START + " = ?, "
            + ACTIVITY_COLUMN_DATE_FINISH + " = ?, "
            + ACTIVITY_COLUMN_DESCRIPTION + " = ?, "
            + ACTIVITY_COLUMN_ACTIVITY_TYPE_ID + " = ?, "
            + ACTIVITY_COLUMN_STATUS + " = ? "
            + "WHERE + " + ACTIVITY_COLUMN_ID + " = ?";

    public static final String FINISH_ACTIVITY = "UPDATE " + ACTIVITY_TABLE + " SET "
            + ACTIVITY_COLUMN_DATE_FINISH + " = ?, "
            + ACTIVITY_COLUMN_STATUS + " = ? "
            + "WHERE + " + ACTIVITY_COLUMN_ID + " = ?";

    @Override
    public int create(Activity activity) {
        return create(INSERT, preparedStatement -> {
            preparedStatement.setInt(1, activity.getUser().getId());
            preparedStatement.setString(2, activity.getDescription());
            preparedStatement.setString(3, activity.getDateStart());
            preparedStatement.setInt(4, activity.getActivityType().getId());
            preparedStatement.setString(5, activity.getStatus().toString());
        });
    }

    @Override
    public boolean update(Activity activity) {
        return updateOrDelete(UPDATE, preparedStatement -> {
            preparedStatement.setInt(1, activity.getUser().getId());
            preparedStatement.setString(2, activity.getDateStart());
            preparedStatement.setString(3, activity.getDateFinish());
            preparedStatement.setString(4, activity.getDescription());
            preparedStatement.setInt(5, activity.getActivityType().getId());
            preparedStatement.setString(6, activity.getStatus().toString());
            preparedStatement.setInt(7, activity.getId());
        });
    }

    @Override
    public boolean delete(int id) {
        return updateOrDelete(DELETE, preparedStatement -> preparedStatement.setInt(1, id));
    }

    @Override
    public List<Activity> findAll() {
        return findAll(SELECT_ALL, new ActivityMapper());
    }


    @Override
    public List<Activity> getActivityByUserId(int id) {
        return findAll(SELECT_ACTIVITY_BY_USER_ID, preparedStatement -> preparedStatement.setInt(1, id), new ActivityMapper());
    }

    @Override
    public List<Activity> getActivityByStatus(ActivityStatus status) {
        return findAll(SELECT_ACTIVITY_BY_STATUS, preparedStatement -> preparedStatement.setString(1, status.toString()), new ActivityMapper());
    }

    @Override
    public List<Activity> getActivityByStatusAndUserId(ActivityStatus status, int userId) {
        return findAll(SELECT_ACTIVITY_BY_STATUS_AND_BY_USER_ID, preparedStatement -> {
            preparedStatement.setString(1, status.toString());
            preparedStatement.setInt(2, userId);
        }, new ActivityMapper());
    }

    @Override
    public boolean finishActivity(int id, ActivityStatus status, String dateFinish) {
        return updateOrDelete(FINISH_ACTIVITY, preparedStatement -> {
            preparedStatement.setString(1, dateFinish);
            preparedStatement.setString(2, status.toString());
            preparedStatement.setInt(3, id);
        });
    }
}
