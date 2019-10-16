package dao.mappers;

import dao.JdbcImpl.ActivityRequestSqlDao;
import model.ActivityRequest;
import model.enums.RequestType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityRequestMapper implements Mapper<model.ActivityRequest> {
    @Override
    public ActivityRequest getEntity(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return new ActivityRequest(resultSet.getInt(ActivityRequestSqlDao.ACTIVITY_REQUEST_COLUMN_ID),
                new UserMapper().getEntity(resultSet),
                RequestType.valueOf(resultSet.getString(ActivityRequestSqlDao.ACTIVITY_REQUEST_COLUMN_REQUEST_TYPE)),
                resultSet.getString(ActivityRequestSqlDao.ACTIVITY_REQUEST_COLUMN_CREATION_DATE));
    }

}
