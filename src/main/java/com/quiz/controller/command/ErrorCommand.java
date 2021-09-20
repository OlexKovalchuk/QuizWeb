package com.quiz.controller.command;

import com.quiz.controller.utils.ErrorMessage;
import com.quiz.controller.utils.Pages;
import com.quiz.controller.utils.WebPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ErrorCommand implements Command {

    @Override
    public WebPath execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        if (request.getAttribute("errorMessage") != null) {
            request.setAttribute("errorMessage",  request.getAttribute("errorMessage"));
        }
        else {
            request.setAttribute("errorMessage", new ErrorMessage());
        }
        return new WebPath(Pages.ERROR, WebPath.DispatchType.FORWARD);

    }
}
