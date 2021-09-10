package com.quiz.DB;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBManager {
    private static BasicDataSource ds = new BasicDataSource();

    public static void main(String[] args) throws SQLException {

        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO topic(name) value ('Math')");
        statement.executeUpdate();
    }

    static {
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/mydb?autoReconnect=true");
        ds.setUsername("Sasha");
        ds.setPassword("12345678");
    }

    private DBManager() {
    }
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}