package com.quiz.controller.command;

import com.quiz.controller.utils.Pages;
import com.quiz.controller.utils.WebPath;
import com.quiz.service.QuizService;
import com.quiz.service.TopicService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class EditQuizCommand implements Command {
    @Override
    public WebPath execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        QuizService quizService = new QuizService();
        TopicService topicService = new TopicService();
        request.setAttribute("topics",topicService.getAllTopics());
        request.setAttribute("quiz",  quizService.getQuiz(Integer.parseInt(request.getParameter("id"))));
        return new WebPath(Pages.EDIT_QUIZ, WebPath.DispatchType.FORWARD);
    }
}
