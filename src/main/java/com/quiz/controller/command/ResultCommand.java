package com.quiz.controller.command;

import com.quiz.controller.utils.Pages;
import com.quiz.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class ResultCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        uploadUserToSession(request);
        return Pages.RESULT;
    }

    private void uploadUserToSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (request.getParameter("page")==null) {
            PaginationCommand.pagination(request, 1, ((User) request.getSession().getAttribute("user")).getId());
        } else {
            int page = Integer.parseInt(request.getParameter("page"));
            PaginationCommand.pagination(request, page, ((User) request.getSession().getAttribute("user")).getId());
        }
    }
}
