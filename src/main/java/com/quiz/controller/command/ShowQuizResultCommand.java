package com.quiz.controller.command;

import com.quiz.controller.utils.WebPath;
import com.quiz.entity.Result;
import com.quiz.entity.User;
import com.quiz.service.ResultService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ShowQuizResultCommand implements Command{
    @Override
    public WebPath execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultService resultService = new ResultService();
        Result result = resultService.getUserResultById(((User)request.getSession().getAttribute("user")).getId());
        request.setAttribute("result",result);
        return null;
    }
}
