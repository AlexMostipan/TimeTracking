package dao.dbcp;

import java.sql.Connection;

public interface ConnectionManager {
    Connection getConnection();
}
