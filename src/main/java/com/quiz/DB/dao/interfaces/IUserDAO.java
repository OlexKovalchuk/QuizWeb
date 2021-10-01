package com.quiz.DB.dao.interfaces;

import com.quiz.entity.User;
import com.quiz.web.utils.Pageable;

import java.util.List;

public interface IUserDAO extends IAbstractDAO<User> {
    boolean updateUserInfo(User user);

    boolean isUserExist(String email);

    boolean blockUser(int id, int block);

    User getUserByEmail(String email);

    int getUsersCount();

    List<User> getAllUsers(int id, Pageable pageable);
}