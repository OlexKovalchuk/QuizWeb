package com.quiz.controller.command;

import com.quiz.DB.dao.UserDAO;
import com.quiz.controller.utils.Pages;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class BlockUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        int block = Integer.parseInt(request.getParameter("block"));
        UserDAO.blockUser(id,block);
        request.setAttribute("users", UserDAO.getAllUsers());
        return Pages.LIST_USERS;
    }
}
