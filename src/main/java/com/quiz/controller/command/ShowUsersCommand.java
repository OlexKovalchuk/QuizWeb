package com.quiz.controller.command;

import com.quiz.DB.dao.UserDAO;
import com.quiz.controller.utils.Pages;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ShowUsersCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        request.setAttribute("users", UserDAO.getAllUsers());
        return Pages.LIST_USERS;
    }
}
