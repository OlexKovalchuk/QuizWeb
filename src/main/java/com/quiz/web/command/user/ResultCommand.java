package com.quiz.web.command.user;

import com.quiz.web.command.Command;
import com.quiz.web.utils.Pageable;
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
        Pageable pageable = new Pageable.Builder()
                .page(request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1)
                .size(5)
                .sort(request.getParameter("sort") != null ? request.getParameter("sort") : "score")
                .ASC()
                .build();
        ResultService resultService = new ResultService();

        request.setAttribute("sort", pageable.getSort());
        request.setAttribute("page", pageable.getPage());
        request.setAttribute("pagesCount", (int) Math.ceil(resultService.getUserResultsCount(user.getId()) / 5.0));
        request.setAttribute("userResults", resultService.getUserResultsById(user.getId(),pageable));

        return new WebPath(Pages.RESULT, WebPath.DispatchType.FORWARD);
    }
}