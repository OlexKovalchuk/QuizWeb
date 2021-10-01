package com.quiz.web.command.user;

import com.quiz.service.UserService;
import com.quiz.web.command.Command;
import com.quiz.web.utils.Pages;
import com.quiz.web.utils.WebPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ShowUserInfoCommand implements Command {

    @Override
    public WebPath execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        request.setAttribute("user",userService.getUserById(Integer.parseInt(request.getParameter("id"))));

        return  new WebPath(Pages.USER_EDIT, WebPath.DispatchType.FORWARD) ;
    }
}