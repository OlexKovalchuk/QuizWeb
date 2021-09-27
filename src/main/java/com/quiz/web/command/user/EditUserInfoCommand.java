package com.quiz.web.command.user;

import com.quiz.entity.Role;
import com.quiz.entity.User;
import com.quiz.service.UserService;
import com.quiz.web.command.Command;
import com.quiz.web.utils.WebPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class EditUserInfoCommand implements Command {
    @Override
    public WebPath execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setId(Integer.parseInt(request.getParameter("id")));
        user.setName(request.getParameter("name"));
        user.setSurname(request.getParameter("surname"));
        user.setRole(Role.getRoleByName(request.getParameter("role")));
        UserService userService = new UserService();
        userService.updateUserInfo(user);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        return new WebPath(WebPath.WebPageEnum.STAND.getPath(), WebPath.DispatchType.STAND);
    }
}