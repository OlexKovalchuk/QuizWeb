package com.quiz.DB.dao;

import com.quiz.DB.DBManager;
import com.quiz.entity.Result;
import com.quiz.entity.Role;
import com.quiz.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public UserDAO() {


    }

    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("Select * from user")
        ) {
            ResultSet resultSet = statement.executeQuery();
            User user;
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setPassword(resultSet.getString("password"));
                user.setLogin(resultSet.getString("login"));
                user.setRole(Role.getRoleFromString(resultSet.getString("role")));
                user.setBlock(resultSet.getInt("block"));
                user.setCreationDate(resultSet.getDate("create_date"));
                try (PreparedStatement statement1 = connection.prepareStatement("Select AVG(score) AS avgscore from " +
                        "result" +
                        " where user_id=?")) {
                    statement1.setInt(1, user.getId());
                    ResultSet resultSet1 = statement1.executeQuery();
                    resultSet1.next();
                    user.setAverageScore(resultSet1.getDouble("avgscore"));
                    resultSet1.close();
                }
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void insertUser(User user) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into user(login,password,role,name," +
                     "surname) value (?,?,?,?,?,?)")
        ) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole().getRoleName());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getSecondName());
            statement.setDate(6, user.getCreationDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User getUserByLogin(String login) {
        ResultSet resultSet = null;
        User user = null;
        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("Select * from user where login=?")
        ) {
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            resultSet.next();
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(Role.getRoleFromString(resultSet.getString("role")));
            user.setFirstName(resultSet.getString("name"));
            user.setSecondName(resultSet.getString("surname"));
            user.setBlock(resultSet.getInt("block"));
            user.setCreationDate(resultSet.getDate("create_date"));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void main(String[] args) {
        System.out.println(getUsersCount(1));
    }

    public static void blockUser(int id, int block) {
        List<Result> results = new ArrayList<>();
        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE user SET block=? where id=?")
        ) {
            statement.setInt(1, block == 0 ? 1 : 0);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static int getUsersCount(int userdId) {
        int count = 0;
        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("Select COUNT(*) from result where user_id=?"
             )
        ) {
            statement.setInt(1, userdId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                return  resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static List<Result> getUserResults(int id, int offset) {
        List<Result> results = new ArrayList<>();
        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("Select * from result where user_id=? limit 5 " +
                     "offset ?")
        ) {
            statement.setInt(1, id);
            statement.setInt(2, offset);
            try (ResultSet resultSet = statement.executeQuery()) {
                Result result;
                while (resultSet.next()) {
                    result = new Result();
                    result.setId(resultSet.getInt("id"));
                    result.setStartDate(resultSet.getTimestamp("start_date"));
                    result.setCompleteDate(resultSet.getTimestamp("complete_date"));
                    result.setScore(resultSet.getInt("score"));
                    result.setUserId(resultSet.getInt("user_id"));
                    result.setTestId(resultSet.getInt("test_id"));
                    try (PreparedStatement statement1 = connection.prepareStatement("Select topic_id,header from " +
                            "test" +
                            " where id=?")) {
                        statement1.setInt(1, result.getTestId());
                        ResultSet resultSet1 = statement1.executeQuery();
                        resultSet1.next();
                        result.setTestHeader(resultSet1.getString("header"));
                        try (PreparedStatement statement2 = connection.prepareStatement("Select * from topic " +
                                "where " +
                                "id=?")) {
                            statement2.setInt(1, resultSet1.getInt("topic_id"));
                            ResultSet resultSet2 = statement2.executeQuery();
                            resultSet2.next();
                            result.setTopicName(resultSet2.getString("name"));
                        }
                    }
                    results.add(result);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public static List<String> getAllLogins() {
        List<String> list = new ArrayList<>();
        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("Select user.login from user")
        ) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(resultSet.getString("login"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean isUserExist(String login) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("Select user.login from user where login=?")
        ) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void updateUser(User user) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE user SET login=?, name=?," +
                     "surname=?," +
                     "role=?,password=? where id=?;")
        ) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getSecondName());
            statement.setString(4, user.getRole().getRoleName());
            statement.setString(5, user.getPassword());
            statement.setInt(6, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUserByLogin(String login) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM user where login=?; ")
        ) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
