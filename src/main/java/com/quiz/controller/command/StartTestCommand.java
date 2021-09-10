package com.quiz.controller.command;

import com.quiz.DB.dao.TestDAO;
import com.quiz.controller.utils.Pages;
import com.quiz.entity.Result;
import com.quiz.entity.Test;
import com.quiz.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;

public class StartTestCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        Test test = TestDAO.getTest(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("test", test);
        Result result = new Result();
        result.setTestId(test.getId());
        result.setStartDate(new Timestamp(System.currentTimeMillis()));
        result.setUserId(((User)request.getSession().getAttribute("user")).getId());
        result.setScore(0);
        result.setCompleteDate(new Timestamp(System.currentTimeMillis()));
        TestDAO.insertResult(result);
        return Pages.TEST;
    }
}
