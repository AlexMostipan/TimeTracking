package dao.mappers;

import dao.JdbcImpl.ProfessionSqlDao;
import model.Profession;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfessionMapper implements Mapper<Profession> {

    @Override
    public Profession getEntity(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return new Profession(resultSet.getInt(ProfessionSqlDao.PROFESSION_COLUMN_PROFESSION_ID),
                resultSet.getString(ProfessionSqlDao.PROFESSION_COLUMN_PROFESSION),
                resultSet.getString(ProfessionSqlDao.PROFESSION_COLUMN_SKILL),
                resultSet.getInt(ProfessionSqlDao.PROFESSION_COLUMN_EXPERIENCE)
        );
    }

}
