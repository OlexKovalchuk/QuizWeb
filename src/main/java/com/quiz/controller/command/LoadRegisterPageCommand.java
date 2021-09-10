package com.quiz.controller.command;

import com.quiz.controller.utils.Pages;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoadRegisterPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.getSession(true).setAttribute("currentPage", "register");
        return Pages.REGISTER;
    }
}
