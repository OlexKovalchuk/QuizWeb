package com.quiz.controller.command;

import com.quiz.controller.utils.Encryptor;
import com.quiz.controller.utils.WebPath;
import com.quiz.entity.Role;
import com.quiz.entity.User;
import com.quiz.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

public class RegisterCommand implements Command {
    @Override
    public WebPath execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        HttpSession session = request.getSession(true);
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        User user = new User();
        user.setName(name);
        user.setRole(Role.STUDENT);
        try {
            user.setPassword(Encryptor.encrypt(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        user.setSurname(surname);
        user.setEmail(email);
        user.setBlock(0);
        user.setCreationDate(new Date(System.currentTimeMillis()));
        UserService userService = new UserService();

        userService.insertUser(user);
        session.setAttribute("user", user);
        return new WebPath(WebPath.WebPageEnum.HOME.getPath(), WebPath.DispatchType.REDIRECT);

    }
}
