package com.quiz.DB;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class DBConnectionPool {
    private static final BasicDataSource ds = new BasicDataSource();
    private static final Properties property = new Properties();
    private static final String DATABASE_PROPERTY_LOCATION = "C:\\Users\\Oleksandr\\AppData\\Local\\Temp\\Quiz\\src" +
            "\\main\\resources\\database.properties";
    public static final Logger logger = Logger.getLogger(DBConnectionPool.class);


    //Setting BasicDataSource properties
    static {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(DATABASE_PROPERTY_LOCATION);
            property.load(fis);
            ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
            ds.setUrl(property.getProperty("db.url"));
            ds.setUsername(property.getProperty("db.user"));
            ds.setPassword(property.getProperty("db.password"));
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

    private DBConnectionPool() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}