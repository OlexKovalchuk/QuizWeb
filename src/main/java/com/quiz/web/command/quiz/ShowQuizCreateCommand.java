package com.quiz.web.command.quiz;

import com.quiz.web.command.Command;
import com.quiz.web.utils.Pages;
import com.quiz.web.utils.WebPath;
import com.quiz.service.TopicService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ShowQuizCreateCommand implements Command {
    @Override
    public WebPath execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        TopicService topicService = new TopicService();
        request.setAttribute("topics", topicService.getAllTopics());
        return new WebPath(Pages.TEST_CREATE, WebPath.DispatchType.FORWARD);
    }
}