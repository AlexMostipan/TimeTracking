package dao.JdbcImpl;

import dao.TransactionManager;
import dao.interfaces.ActivityTypeDao;
import dao.mappers.ActivityTypeMapper;
import model.ActivityType;

import java.util.List;

public class ActivityTypeSqlDao extends AbstractSqlDao<ActivityType> implements ActivityTypeDao {


    public ActivityTypeSqlDao(TransactionManager transactionManager) {
        super(transactionManager);
    }

    public static final String ACTIVITY_TYPE_TABLE = "activity_type";

    public static final String ACTIVITY_TYPE_COLUMN_ID = "activity_type_id";
    public static final String ACTIVITY_TYPE_COLUMN_TITILE = "title";
    public static final String SELECT_ALL = "SELECT * FROM activity_type";
    private static final String SELECT_ACTIVITY_TYPE_BY_ID = "SELECT * FROM activity_type WHERE activity_type.activity_type_id  = ?";
    public static final String SELECT_ACTIVITY_TYPE_BY_TITLE = "SELECT * FROM " + ACTIVITY_TYPE_TABLE + " WHERE " + ACTIVITY_TYPE_COLUMN_TITILE + " LIKE  ?";
    private static final String INSERT = "INSERT INTO " + ACTIVITY_TYPE_TABLE + " ("
            + ACTIVITY_TYPE_COLUMN_ID + ", "
            + ACTIVITY_TYPE_COLUMN_TITILE +
            ") "
            + "VALUES (?, ?) ";


    public static final String UPDATE = "UPDATE " + ACTIVITY_TYPE_TABLE + " SET "
            + ACTIVITY_TYPE_COLUMN_TITILE + " = ?, "
            + "WHERE + " + ACTIVITY_TYPE_COLUMN_ID + " = ?";
    private static final String DELETE = "DELETE FROM " + ACTIVITY_TYPE_TABLE + " WHERE " + ACTIVITY_TYPE_COLUMN_ID + " = ?";


    @Override
    public int create(ActivityType activityType) {
        return create(INSERT, preparedStatement -> {
            preparedStatement.setInt(1, activityType.getId());
            preparedStatement.setString(2, activityType.getTitle());
        });
    }


    @Override
    public boolean update(ActivityType activityType) {
        return updateOrDelete(UPDATE, preparedStatement -> {
            preparedStatement.setString(1, activityType.getTitle());
            preparedStatement.setInt(2, activityType.getId());

        });
    }

    @Override
    public List<ActivityType> findAll() {
        return findAll(SELECT_ALL, new ActivityTypeMapper());
    }

    @Override
    public boolean delete(int id) {
        return updateOrDelete(DELETE, preparedStatement -> preparedStatement.setInt(1, id));
    }

    @Override
    public ActivityType findByTitle(String title) {
        return find(SELECT_ACTIVITY_TYPE_BY_TITLE, preparedStatement -> preparedStatement.setString(1, title), new ActivityTypeMapper());
    }

    @Override
    public ActivityType findById(int id) {
        return find(SELECT_ACTIVITY_TYPE_BY_ID, preparedStatement -> preparedStatement.setInt(1, id), new ActivityTypeMapper());
    }
}
