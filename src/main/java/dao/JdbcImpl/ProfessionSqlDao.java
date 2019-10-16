package dao.JdbcImpl;

import dao.TransactionManager;
import dao.interfaces.ProfessionDao;
import model.Profession;

public class ProfessionSqlDao extends AbstractSqlDao<Profession> implements ProfessionDao {
    public ProfessionSqlDao(TransactionManager transactionManager) {
        super(transactionManager);
    }

    public static final String PROFESSION_TABLE = "profession";
    public static final String PROFESSION_COLUMN_PROFESSION_ID = "profession_id";
    public static final String PROFESSION_COLUMN_SKILL = "skill";
    public static final String PROFESSION_COLUMN_PROFESSION = "profession";
    public static final String PROFESSION_COLUMN_EXPERIENCE = "experience";


    private static final String INSERT = "INSERT INTO " + PROFESSION_TABLE + " ("
            + PROFESSION_COLUMN_PROFESSION_ID + ", "
            + PROFESSION_COLUMN_SKILL + ", "
            + PROFESSION_COLUMN_PROFESSION + ", "
            + PROFESSION_COLUMN_EXPERIENCE +
            ") "
            + "VALUES (?, ?, ?, ?) ";

    public static final String UPDATE = "UPDATE " + PROFESSION_TABLE + " SET "
            + PROFESSION_COLUMN_SKILL + " = ?, "
            + PROFESSION_COLUMN_PROFESSION + " = ?, "
            + PROFESSION_COLUMN_EXPERIENCE + " = ?, "
            + "WHERE + " + PROFESSION_COLUMN_PROFESSION_ID + " = ?";
    private static final String DELETE = "DELETE FROM " + PROFESSION_TABLE + " WHERE " + PROFESSION_COLUMN_PROFESSION_ID + " = ?";

    @Override
    public int create(Profession profession) {
        return create(INSERT, preparedStatement -> {
            preparedStatement.setInt(1, profession.getId());
            preparedStatement.setString(2, profession.getSkill());
            preparedStatement.setString(3, profession.getProfession());
            preparedStatement.setInt(4, profession.getExperience());

        });
    }

    @Override
    public boolean update(Profession profession) {
        return updateOrDelete(UPDATE, preparedStatement -> {
            preparedStatement.setString(1, profession.getSkill());
            preparedStatement.setString(2, profession.getProfession());
            preparedStatement.setInt(3, profession.getExperience());
            preparedStatement.setInt(4, profession.getId());

        });
    }

    @Override
    public boolean delete(int id) {
        return updateOrDelete(DELETE, preparedStatement -> preparedStatement.setInt(1, id));
    }
}
