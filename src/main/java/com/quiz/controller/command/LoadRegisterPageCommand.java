package com.quiz.controller.command;

import com.quiz.controller.utils.Pages;
import com.quiz.controller.utils.WebPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoadRegisterPageCommand implements Command {

    @Override
    public WebPath execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        return new WebPath(Pages.REGISTER, WebPath.DispatchType.FORWARD);
    }
}
