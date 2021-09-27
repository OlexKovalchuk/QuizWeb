package com.quiz.web.command.user;

import com.quiz.web.command.Command;
import com.quiz.web.utils.Pages;
import com.quiz.web.utils.WebPath;
import com.quiz.entity.Result;
import com.quiz.entity.User;
import com.quiz.service.ResultService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class ResultCommand implements Command {
    @Override
    public WebPath execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        List<Result> results = null;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        ResultService resultService = new ResultService();
        String sort = request.getParameter("sort");
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        if (sort != null) {
            switch (sort) {
                case "topic":
                    results = resultService.getUserResultsWithPaginationByTopic(user.getId(),
                            page);
                    break;
                case "score":
                    results = resultService.getUserResultsWithPaginationByScore(user.getId(),
                            page);
                    break;
                case "date":
                    results = resultService.getUserResultsWithPaginationByDate(user.getId(),
                            page);
                    break;
                case "quiz":
                    results = resultService.getUserResultsWithPaginationByQuiz(user.getId(),
                            page);
                    break;
                default:
                    results = resultService.getUserResultsWithPagination(user.getId(), page);
                    break;

            }
        } else {
            results = resultService.getUserResultsWithPagination(user.getId(), page);
        }
        request.setAttribute("sort",sort);
        request.setAttribute("page",page);
        request.setAttribute("pagesCount", (int) Math.ceil(resultService.getUserResultsCount(user.getId()) / 5.0));

        request.setAttribute("userResults", results);
        return new WebPath(Pages.RESULT, WebPath.DispatchType.FORWARD);

    }

}