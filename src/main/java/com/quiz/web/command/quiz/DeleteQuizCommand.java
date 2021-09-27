package com.quiz.web.command.quiz;

import com.quiz.service.QuizService;
import com.quiz.web.command.Command;
import com.quiz.web.utils.WebPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteQuizCommand implements Command {
    @Override
    public WebPath execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuizService quizService = new QuizService();
        quizService.deleteQuiz(Integer.parseInt(request.getParameter("id")));
        return new WebPath(WebPath.WebPageEnum.HOME.getPath(), WebPath.DispatchType.REDIRECT);
    }
}