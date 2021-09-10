package com.quiz.controller.command;

import com.quiz.DB.dao.UserDAO;
import com.quiz.controller.utils.Encryptor;
import com.quiz.controller.utils.Pages;
import com.quiz.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class EditPersonalInfoCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        User user = (User) request.getSession().getAttribute("user");
        String login = request.getParameter("login");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String password = request.getParameter("password");
        if (login.length()>0) {
            user.setLogin(login);
        }
        if (name.length()>0) {
            user.setFirstName(name);
        }
        if (surname.length()>0) {
            user.setSecondName(surname);
        }
        if (password.length()>0) {
            try {
                user.setPassword(Encryptor.encrypt(password));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        UserDAO.updateUser(user);
        return Pages.PROFILE;
    }
}
