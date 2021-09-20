package com.quiz.controller.command;

import com.quiz.controller.utils.Pages;
import com.quiz.controller.utils.WebPath;
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
        String sort = request.getParameter("sort");
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        if (sort != null) {
            switch (sort) {
                case "topic":
                    quizList = quizService.getAllQuizWithPaginationByTopic(page);
                    break;
                case "duration":
                    quizList = quizService.getAllQuizWithPaginationByDuration(page);
                    break;
                case "date":
                    quizList = quizService.getAllQuizWithPaginationByDate(page);
                    break;
                case "difficult":
                    quizList = quizService.getAllQuizWithPaginationByDifficult(page);
                    break;
                default:
                    quizList = quizService.getAllQuizWithPaginationByQuestion(page);
                    break;
            }
        } else {
            quizList = quizService.getAllQuizWithPaginationByQuestion(page);
        }
        request.setAttribute("sort", sort);
        request.setAttribute("page", page);
        request.setAttribute("pagesCount", (int) Math.ceil(quizService.getQuizCount()/4.0));
        request.setAttribute("quizzes", quizList);
        return new WebPath(Pages.HOME, WebPath.DispatchType.FORWARD);

    }
}
