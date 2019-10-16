package dao.JdbcImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface PreparedStatementConsumer {

    void prepare(PreparedStatement preparedStatement) throws SQLException;
}