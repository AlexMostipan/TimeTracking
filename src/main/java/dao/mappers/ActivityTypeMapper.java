package dao.mappers;

import dao.JdbcImpl.ActivityTypeSqlDao;

import model.ActivityType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityTypeMapper implements Mapper<ActivityType> {

    @Override
    public ActivityType getEntity(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return new ActivityType(resultSet.getInt(ActivityTypeSqlDao.ACTIVITY_TYPE_COLUMN_ID),
                resultSet.getString(ActivityTypeSqlDao.ACTIVITY_TYPE_COLUMN_TITILE)
        );
    }
}
