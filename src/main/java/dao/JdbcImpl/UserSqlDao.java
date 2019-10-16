package dao.JdbcImpl;

import dao.TransactionManager;
import dao.interfaces.UserDao;
import dao.mappers.UserMapper;
import model.User;

import java.util.List;

public class UserSqlDao extends AbstractSqlDao<User> implements UserDao {
    public UserSqlDao(TransactionManager transactionManager) {
        super(transactionManager);
    }
    public static final String USER_TABLE = "user";

    public static final String USER_COLUMN_ID = "user_id";
    public static final String USER_COLUMN_USERNAME = "username";
    public static final String USER_COLUMN_PASSWORD = "password";
    public static final String USER_COLUMN_EMAIL = "email";
    public static final String USER_COLUMN_ROLE = "role";
    public static final String USER_COLUMN_PROFESSION_ID = "profession_id";

    private static final String SELECT_USER_BY_USER_ID = "SELECT * FROM " + USER_TABLE + " WHERE " + USER_COLUMN_ID + " = ?";
    private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM " + USER_TABLE + " where " + USER_COLUMN_USERNAME + " = ?";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM " + USER_TABLE + " where " + USER_COLUMN_EMAIL + " = ?";
    private static final String INSERT = "INSERT INTO " + USER_TABLE + " ("
            + USER_COLUMN_USERNAME + ", "
            + USER_COLUMN_PASSWORD + ", "
            + USER_COLUMN_EMAIL + ", "
            + USER_COLUMN_PROFESSION_ID + ", "
            + USER_COLUMN_ROLE +
            ") "
            + "VALUES (?, ?, ?, ?, ?) ";

    public static final String UPDATE = "UPDATE " + USER_TABLE + " SET "
            + USER_COLUMN_USERNAME + " = ?, "
            + USER_COLUMN_PASSWORD + " = ?, "
            + USER_COLUMN_EMAIL + " = ?, "
            + USER_COLUMN_PROFESSION_ID + " = ?, "
            + USER_COLUMN_ROLE + " = ? "
            + "WHERE + " + USER_COLUMN_ID + " = ?";

    private static final String DELETE = "DELETE FROM " + USER_TABLE + " WHERE " + USER_COLUMN_ID + " = ?";
    private static final String SELECT_ALL = "SELECT * FROM " + USER_TABLE;

    @Override
    public int create(User user ) {
        return create(INSERT, preparedStatement -> {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getProfessionId());
            preparedStatement.setString(5, user.getRole().toString());
        });

    }

    @Override
    public boolean update(User user) {
        return updateOrDelete(UPDATE, preparedStatement -> {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getProfessionId());
            preparedStatement.setString(5, user.getRole().toString());
            preparedStatement.setInt(6, user.getId());
        });
    }

    @Override
    public User findByEmail(String email) {
        return find(SELECT_USER_BY_EMAIL, preparedStatement -> preparedStatement.setString(1, email), new UserMapper());
    }

    @Override
    public List<User> findAll() {
        return findAll(SELECT_ALL, new UserMapper());
    }


    @Override
    public User findById(int id) {
        return find(SELECT_USER_BY_USER_ID, preparedStatement -> preparedStatement.setInt(1, id), new UserMapper());
    }

    @Override
    public User getUserByUserName(String username) {
        return find(SELECT_USER_BY_USERNAME, preparedStatement -> preparedStatement.setString(1, username), new UserMapper());
    }

    @Override
    public boolean delete(int userId) {
        return updateOrDelete(DELETE, preparedStatement -> preparedStatement.setInt(1, userId));
    }
}
