package com.quiz.DB.dao;

import com.quiz.DB.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TopicDAO {
    public TopicDAO() {

    }

    public static void insertTopic(String name) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into topic(name) values(?);")) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static String getTopicName(int id) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT name FROM topic where id=?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "none";
    }

    public static void deleteTopic(int id) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM topic where id=?;")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}