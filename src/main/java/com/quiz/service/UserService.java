package com.quiz.service;

import com.quiz.DB.*;
import com.quiz.DB.dao.impl.UserDAO;
import com.quiz.entity.User;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final DAOFactory factory;
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(UserService.class);
    }

    public UserService() {
        this.factory = new MySqlDAOFactory();
    }

    public List<User> getAllUsersWithPagination(int page) {
        try (DBConnection conn = factory.createConnection()) {
            UserDAO userDAO = factory.createUserDAO(conn);
            return userDAO.getAllUsers((page-1)*10, "", "");
        }
    }

    @Sort(type = "order by", param = "user.create_date")
    public List<User> getAllUsersWithPaginationByDate(int page) {
        try (DBConnection conn = factory.createConnection()) {
            Sort sortAnnotation =
                    (Sort) UserService.class.getMethod("getAllUsersWithPaginationByDate", int.class).getAnnotation(Sort.class);
            UserDAO userDAO = factory.createUserDAO(conn);
            return userDAO.getAllUsers((page-1)*10, sortAnnotation.type(), sortAnnotation.param());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Sort(type = "order by", param = "avgscore")
    public List<User> getAllUsersWithPaginationByScore(int page) {
        try (DBConnection conn = factory.createConnection()) {
            Sort sortAnnotation =
                    (Sort) UserService.class.getMethod("getAllUsersWithPaginationByScore", int.class).getAnnotation(Sort.class);
            UserDAO userDAO = factory.createUserDAO(conn);
            return userDAO.getAllUsers((page-1)*10, sortAnnotation.type(), sortAnnotation.param());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Sort(type = "order by", param = "user.block")
    public List<User> getAllUsersWithPaginationByBlock(int page) {
        try (DBConnection conn = factory.createConnection()) {
            Sort sortAnnotation =
                    (Sort) UserService.class.getMethod("getAllUsersWithPaginationByBlock", int.class).getAnnotation(Sort.class);
            UserDAO userDAO = factory.createUserDAO(conn);
            return userDAO.getAllUsers((page-1)*10, sortAnnotation.type(), sortAnnotation.param());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public int getUsersCount() {
        try (DBConnection conn = factory.createConnection()) {
            UserDAO userDAO = factory.createUserDAO(conn);
            return userDAO.getUsersCount();
        }
    }

    public boolean insertUser(User user) {
        try (DBConnection conn = factory.createConnection()) {
            UserDAO userDAO = factory.createUserDAO(conn);
          return  userDAO.create(user);
        }
    }

    public User getUserByEmail(String email) {
        try (DBConnection conn = factory.createConnection()) {
            UserDAO userDAO = factory.createUserDAO(conn);
            return userDAO.getUserByEmail(email);
        }
    }
    public User getUserById(int id) {
        try (DBConnection conn = factory.createConnection()) {
            UserDAO userDAO = factory.createUserDAO(conn);
            return userDAO.findById(id);
        }
    }

    public boolean blockUser(int id, int block) {
        try (DBConnection conn = factory.createConnection()) {
            UserDAO userDAO = factory.createUserDAO(conn);
          return  userDAO.blockUser(id, block);
        }
    }

    public boolean isUserExist(String email) {
        try (DBConnection conn = factory.createConnection()) {
            UserDAO userDAO = factory.createUserDAO(conn);
            return userDAO.isUserExist(email);
        }
    }

    public boolean updateUser(User user) {
        try (DBConnection conn = factory.createConnection()) {
            UserDAO userDAO = factory.createUserDAO(conn);
          return  userDAO.update(user);
        }
    }

    public boolean deleteUserById(int id) {
        try (DBConnection conn = factory.createConnection()) {
            UserDAO userDAO = factory.createUserDAO(conn);
          return  userDAO.delete(id);
        }
    }



    public boolean updateUserInfo(User user) {
        try (DBConnection conn = factory.createConnection()) {
            UserDAO userDAO = factory.createUserDAO(conn);
          return  userDAO.updateUserInfo(user);
        }
    }
}