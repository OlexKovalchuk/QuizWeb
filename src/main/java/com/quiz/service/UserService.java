package com.quiz.service;

import com.quiz.DB.DAOFactory;
import com.quiz.DB.DBConnection;
import com.quiz.DB.MySqlDAOFactory;
import com.quiz.DB.dao.impl.UserDAO;
import com.quiz.entity.User;
import com.quiz.web.utils.Pageable;
import org.apache.log4j.Logger;

import java.util.List;

public class UserService {
    private final DAOFactory factory;
    public static final Logger logger =Logger.getLogger(UserService.class);


    public UserService() {
        this.factory = new MySqlDAOFactory();
    }

    public List<User> getAllUsers(int id,Pageable pageable) {
        try (DBConnection conn = factory.createConnection()) {
            UserDAO userDAO = factory.createUserDAO(conn);
            return userDAO.getAllUsers(id,pageable);
        }
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