package com.quiz.web.command;

import com.quiz.service.QuizService;
import com.quiz.service.TopicService;
import com.quiz.web.utils.Pageable;
import com.quiz.web.utils.Pages;
import com.quiz.web.utils.WebPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class HomeCommand implements Command {
    @Override
    public WebPath execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        QuizService quizService = new QuizService();
        TopicService topicService = new TopicService();
        int topicId =request.getParameter("topicId")!=null?Integer.parseInt(request.getParameter("topicId")):0;
        Pageable pageable = new Pageable.Builder()
                .page(request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1)
                .size(4)
                .sort(request.getParameter("sort") != null ? request.getParameter("sort") : "count")
                .ASC()
                .build();


        request.setAttribute("sort", pageable.getSort());
        request.setAttribute("page", pageable.getPage());
        request.setAttribute("pagesCount", (int) Math.ceil(quizService.getQuizCount(topicId)/4.0));
        request.setAttribute("topicId", topicId);
        request.setAttribute("topics",topicService.getAllTopics());
        request.setAttribute("quizzes", quizService.getAllQuizzes(topicId,pageable));

        return new WebPath(Pages.HOME, WebPath.DispatchType.FORWARD);
    }
}