package com.quiz.controller.command;

import com.quiz.controller.utils.Pages;
import com.quiz.controller.utils.WebPath;
import com.quiz.entity.Quiz;
import com.quiz.entity.Result;
import com.quiz.entity.User;
import com.quiz.service.QuizService;
import com.quiz.service.ResultService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;

public class StartTestCommand implements Command {
    @Override
    public WebPath execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        QuizService quizService =new QuizService();
        ResultService resultService =new ResultService();
        Quiz quiz = quizService.getQuiz(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("quiz", quiz);
        Result result = new Result();
        result.setTestId(quiz.getId());
        result.setStartDate(new Timestamp(System.currentTimeMillis()));
        result.setUserId(((User)request.getSession().getAttribute("user")).getId());
        result.setScore(0);
        result.setCompleteDate(new Timestamp(System.currentTimeMillis()));
        resultService.insertResult(result);
        return new WebPath(Pages.TEST, WebPath.DispatchType.FORWARD);

    }
}
