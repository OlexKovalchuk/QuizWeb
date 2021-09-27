package com.quiz.web.command.quiz;

import com.quiz.entity.Quiz;
import com.quiz.entity.Result;
import com.quiz.entity.User;
import com.quiz.service.QuizService;
import com.quiz.service.ResultService;
import com.quiz.web.command.Command;
import com.quiz.web.utils.Pages;
import com.quiz.web.utils.WebPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

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
        result.setUserId(((User)request.getSession().getAttribute("user")).getId());
        result.setScore(0);
        resultService.insertResult(result);
        return new WebPath(Pages.TEST, WebPath.DispatchType.FORWARD);

    }
}