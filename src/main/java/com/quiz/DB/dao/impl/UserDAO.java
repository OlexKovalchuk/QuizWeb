package com.quiz.DB.dao.impl;

import com.quiz.DB.dao.interfaces.IUserDAO;
import com.quiz.entity.Role;
import com.quiz.entity.User;
import com.quiz.exceptions.UnsuccessfulQueryException;
import com.quiz.web.utils.Pageable;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.quiz.DB.DBConnection.closeResultSet;

public class UserDAO extends AbstractDAO<User> implements IUserDAO {
    public static final Logger logger =Logger.getLogger(UserDAO.class);


    public UserDAO(Connection connection) {
        super(connection);
    }

    @Override
    protected PreparedStatement getSelectEntityByIdStatement() throws SQLException {
        return connection.prepareStatement("Select * from user where id =?");
    }

    @Override
    protected PreparedStatement getSelectAllEntitiesStatement() throws SQLException {
        return connection.prepareStatement("Select * from user ");
    }

    @Override
    protected PreparedStatement getInsertEntityStatement() throws SQLException {
        return connection.prepareStatement("Insert into  user (email,password, name, surname, create_date " +
                ") values (?,?,?,?,NOW())");

    }

    @Override
    protected PreparedStatement getLastInsertIdStatement() throws SQLException {
        return connection.prepareStatement("Select LAST_INSERT_ID() from user ");

    }

    @Override
    protected PreparedStatement getUpdateStatement() throws SQLException {
        return connection.prepareStatement("UPDATE user SET email=?, name=?," +
                "surname=?," +
                "password=? where id=?;");

    }

    @Override
    protected PreparedStatement getDeleteStatement() throws SQLException {
        return connection.prepareStatement("Delete from user where user.id=?");

    }

    @Override
    protected int getEntityId(User entity) {
        return entity.getId();
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User entity) throws SQLException {
        statement.setString(1, entity.getEmail());
        statement.setString(2, entity.getName());
        statement.setString(3, entity.getSurname());
        statement.setString(4, entity.getPassword());
        statement.setInt(5, entity.getId());
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User entity) throws SQLException {
        statement.setString(1, entity.getEmail());
        statement.setString(2, entity.getPassword());
        statement.setString(3, entity.getName());
        statement.setString(4, entity.getSurname());
    }

    @Override
    protected User getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new User.Builder()
                .setId(resultSet.getInt("id"))
                .setEmail(resultSet.getString("email"))
                .setName(resultSet.getString("name"))
                .setSurname(resultSet.getString("surname"))
                .setRole(Role.getRoleById(resultSet.getInt("role_id")))
                .setBlock(resultSet.getInt("block"))
                .setCreationDate(resultSet.getDate("create_date"))
                .build();
    }



    public List<User> getAllUsers(int id, Pageable pageable) {
        ResultSet resultSet = null;
        String sql = "Select user.id,user.email,user.create_date as date,r" +
                ".name as " +
                "role, user.block," +
                "AVG(res.score) as avgscore from user join role r on user.role_id = r.id left outer join result res " +
                "on user" +
                ".id= " +
                "res.user_id where user.id!=? group by user.id " + pageable.getSortWithOrder() + " Limit " + pageable.getSize() + " " +
                "offset " + pageable.getOffset();
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)
        ) {statement.setInt(1,id);
            resultSet = statement.executeQuery();
            User user;
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(Role.getRoleByName(resultSet.getString("role")));
                user.setBlock(resultSet.getInt("block"));
                user.setCreationDate(resultSet.getDate("date"));
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
                ".name,user.surname,user.block, user.role_id from user  where" +
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
                user.setRole(Role.getRoleById(resultSet.getInt("role_id")));
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


    public boolean blockUser(int id, int block) {
        int changes = 0;
        try (PreparedStatement statement = connection.prepareStatement("UPDATE user SET block=? where id=?")
        ) {
            statement.setInt(1, block);
            statement.setInt(2, id);
            changes = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        return changes > 0;
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



    public boolean updateUserInfo(User user) {
        int changes = 0;
        try (PreparedStatement statement = connection.prepareStatement("UPDATE user SET  name=?," +
                "surname=?," +
                "role_id=? where user.id=?;")
        ) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setInt(3, user.getRole().getId());
            statement.setInt(4, user.getId());
            changes = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        return changes > 0;
    }
}