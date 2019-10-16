package dao;

import java.sql.Connection;


public interface TransactionManager {

    Connection getConnection();

    void closeConnection();

    void startTransaction();

    void endTransaction();
}