package dao.mappers;

import dao.JdbcImpl.UserSqlDao;

import model.User;
import model.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User> {

    @Override
    public User getEntity(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return new User(resultSet.getInt(UserSqlDao.USER_COLUMN_ID),
                Role.valueOf(resultSet.getString(UserSqlDao.USER_COLUMN_ROLE)),
                resultSet.getString(UserSqlDao.USER_COLUMN_USERNAME),
                resultSet.getString(UserSqlDao.USER_COLUMN_EMAIL),
                resultSet.getString(UserSqlDao.USER_COLUMN_PASSWORD),
                resultSet.getInt(UserSqlDao.USER_COLUMN_PROFESSION_ID));
    }

}

