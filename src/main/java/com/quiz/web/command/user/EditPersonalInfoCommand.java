package com.quiz.web.command.user;

import com.quiz.web.command.Command;
import com.quiz.web.utils.Encryptor;
import com.quiz.web.utils.WebPath;
import com.quiz.entity.User;
import com.quiz.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class EditPersonalInfoCommand implements Command {
    @Override
    public WebPath execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        UserService userService = new UserService();
        User user = (User) request.getSession().getAttribute("user");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String password = request.getParameter("password");
        if (email.length()>0) {
            user.setEmail(email);
        }
        if (name.length()>0) {
            user.setName(name);
        }
        if (surname.length()>0) {
            user.setSurname(surname);
        }
        if (password.length()>0) {
            try {
                user.setPassword(Encryptor.encrypt(password));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        userService.updateUser(user);
        return new WebPath(WebPath.WebPageEnum.PROFILE.getPath(), WebPath.DispatchType.REDIRECT);
    }
}