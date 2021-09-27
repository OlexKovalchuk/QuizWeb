package com.quiz.web.command;

import com.quiz.service.TopicService;
import com.quiz.web.utils.Pages;
import com.quiz.web.utils.WebPath;
import com.quiz.entity.Quiz;
import com.quiz.service.QuizService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class HomeCommand implements Command {
    @Override
    public WebPath execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        List<Quiz> quizList = null;
        QuizService quizService = new QuizService();
        TopicService topicService = new TopicService();
        String sort = request.getParameter("sort");
        String topic = request.getParameter("topic")!=null?request.getParameter("topic"):"all";
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        if (sort != null) {
            switch (sort) {
                case "topic":
                    quizList = quizService.getAllQuizWithPaginationByTopic(page,topic);
                    break;
                case "duration":
                    quizList = quizService.getAllQuizWithPaginationByDuration(page,topic);
                    break;
                case "date":
                    quizList = quizService.getAllQuizWithPaginationByDate(page,topic);
                    break;
                case "difficult":
                    quizList = quizService.getAllQuizWithPaginationByDifficult(page,topic);
                    break;
                default:
                    quizList = quizService.getAllQuizWithPaginationByQuestion(page,topic);
                    break;
            }
        } else {
            quizList = quizService.getAllQuizWithPaginationByQuestion(page,topic);
        }
        request.setAttribute("sort", sort);
        request.setAttribute("page", page);
        request.setAttribute("topic", topic);
        request.setAttribute("topics",topicService.getAllTopics());
        request.setAttribute("pagesCount", (int) Math.ceil(quizService.getQuizCount(topic)/4.0));
        request.setAttribute("quizzes", quizList);
        return new WebPath(Pages.HOME, WebPath.DispatchType.FORWARD);
    }
}