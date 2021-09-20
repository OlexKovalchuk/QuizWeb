package com.quiz.DB;

import com.quiz.exceptions.NoConnectionToDbException;
import org.apache.log4j.Logger;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Wrapper class for sql Connection object.
 * Simplifies performing basic operations with the connection to DB
 */
public class SqlConnection implements Closeable {

    private final Connection connection;
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(SqlConnection.class);
    }

    public SqlConnection() {
        try {
            this.connection = DBConnectionPool.getConnection();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new NoConnectionToDbException();
        }
    }

    public void setAutoCommit(boolean autoCommit) {
        if(connection!=null){
            try {
                connection.setAutoCommit(autoCommit);
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    public void commit() {
        if (connection!=null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    public void rollback() {
        if (connection!=null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    public void close(){
        if (connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }
    public static void closeResultSet(ResultSet resultSet){
        try {
            if(resultSet!=null)
                resultSet.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
    public Connection getConnection() {
        return connection;
    }
}
