package com.quiz.DB.dao;

import com.quiz.DB.LogConfigurator;
import com.quiz.entity.Role;
import com.quiz.entity.User;
import com.quiz.exceptions.UnsuccessfulQueryException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.quiz.DB.SqlConnection.closeResultSet;

public class UserDAO {
    private final static Logger logger;
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;

    }

    static {
        logger = LogConfigurator.getLogger(UserDAO.class);
    }

    public List<User> getAllUsers(int offset, String type, String param) {
        ResultSet resultSet = null;
        String sql = "Select user.id,user.email,user.create_date,r" +
                ".name as " +
                "role, user.block," +
                "AVG(res.score) as avgscore from user join role r on user.role_id = r.id left join result res on user" +
                ".id= " +
                "res.user_id group by user.id " + type + " " + param + " Limit 10 offset ? ;";
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, offset);
            resultSet = statement.executeQuery();
            User user;
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(Role.getRoleFromString(resultSet.getString("role")));
                user.setBlock(resultSet.getInt("block"));
                user.setCreationDate(resultSet.getDate("create_date"));
                user.setAverageScore(resultSet.getDouble("avgscore"));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        } finally {
            closeResultSet(resultSet);
        }
        return users;
    }

    public void insertUser(User user) {
        try (PreparedStatement statement = connection.prepareStatement("insert into user(email,password,role_id,name," +
                "surname) value (?,?,1,?,?,?)")
        ) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole().getRoleName());
            statement.setString(4, user.getName());
            statement.setString(5, user.getSurname());
            statement.setDate(6, user.getCreationDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
    }

    public int getUsersCount() {
        ResultSet resultSet = null;
        int count = 0;
        try (PreparedStatement statement = connection.prepareStatement("Select COUNT(*)  as count from user")) {
            resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
        }
        return count;
    }

    public User getUserByEmail(String email) {
        ResultSet resultSet = null;
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement("Select user.id,user.password,user.email," +
                "user" +
                ".name,user.surname,user.block, r.name as role from user join role r on r.id = user.role_id where" +
                " " +
                "email=? ")
        ) {
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.getRoleFromString(resultSet.getString("role")));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setBlock(resultSet.getInt("block"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
        }
        return user;
    }

    public void blockUser(int id, int block) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE user SET block=? where id=?")
        ) {
            statement.setInt(1, block);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }

    }


    public boolean isUserExist(String email) {
        ResultSet resultSet = null;
        boolean isExist = false;
        try (PreparedStatement statement = connection.prepareStatement("Select user.email from user where email=?")
        ) {
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            isExist = resultSet.next();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        } finally {
            closeResultSet(resultSet);
        }
        return isExist;
    }

    public void updateUser(User user) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE user SET email=?, name=?," +
                "surname=?," +
                "password=? where id=?;")
        ) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getName());
            statement.setString(3, user.getSurname());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserByEmail(String email) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM user where email=?; ")
        ) {
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
    }
}
