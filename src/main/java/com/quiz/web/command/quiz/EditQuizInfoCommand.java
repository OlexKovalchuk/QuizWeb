package com.quiz.web.command.quiz;

import com.quiz.entity.Quiz;
import com.quiz.service.QuizService;
import com.quiz.web.command.Command;
import com.quiz.web.utils.WebPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class EditQuizInfoCommand implements Command {

    @Override
    public WebPath execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        Quiz quiz = new Quiz.Builder()
                .id(Integer.parseInt(request.getParameter("id")))
                .header(request.getParameter("header"))
                .description(request.getParameter("description"))
                .topicId(Integer.parseInt(request.getParameter("topic")))
                .difficult(request.getParameter("difficult"))
                .duration(Integer.parseInt(request.getParameter("duration"))).build();
        QuizService quizService = new QuizService();
        quizService.updateQuizInfoById(quiz);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return new WebPath("", WebPath.DispatchType.STAND);
    }
}