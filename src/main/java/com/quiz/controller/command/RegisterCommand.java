package com.quiz.controller.command;

import com.quiz.DB.dao.TestDAO;
import com.quiz.DB.dao.UserDAO;
import com.quiz.controller.utils.Encryptor;
import com.quiz.controller.utils.Pages;
import com.quiz.entity.Role;
import com.quiz.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

public class RegisterCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        HttpSession session = request.getSession(true);
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        User user = new User();
        user.setFirstName(name);
        user.setRole(Role.STUDENT);
        try {
            user.setPassword(Encryptor.encrypt(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        user.setSecondName(surname);
        user.setLogin(login);
        user.setBlock(0);
        user.setCreationDate(new Date(System.currentTimeMillis()));
        UserDAO.insertUser(user);
        session.setAttribute("user", user);
        request.setAttribute("tests", TestDAO.getAllTests());
        return Pages.HOME;
    }
}
