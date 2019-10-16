package dao.mappers;

import dao.JdbcImpl.ActivitySqlDao;
import model.Activity;
import model.enums.ActivityStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityMapper implements Mapper<Activity> {

    @Override
    public Activity getEntity(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return new Activity(resultSet.getInt(ActivitySqlDao.ACTIVITY_COLUMN_ID),
                new UserMapper().getEntity(resultSet),
                new ActivityTypeMapper().getEntity(resultSet),
                ActivityStatus.valueOf(resultSet.getString(ActivitySqlDao.ACTIVITY_COLUMN_STATUS)),
                resultSet.getString(ActivitySqlDao.ACTIVITY_COLUMN_DESCRIPTION),
                resultSet.getString(ActivitySqlDao.ACTIVITY_COLUMN_DATE_START),
                resultSet.getString(ActivitySqlDao.ACTIVITY_COLUMN_DATE_FINISH)
        );
    }

}


