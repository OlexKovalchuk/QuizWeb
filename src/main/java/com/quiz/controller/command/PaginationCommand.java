package com.quiz.controller.command;

import com.quiz.DB.dao.UserDAO;
import com.quiz.controller.utils.Pages;
import com.quiz.entity.Result;
import com.quiz.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class PaginationCommand implements Command {

    final static Logger logger = Logger.getLogger(PaginationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {

        logger.debug("PaginationCommand starts:");
        if (request.getParameter("page").equals("")) {
            logger.error("Page not found, call pagination function");
            pagination(request, 1, ((User) request.getSession().getAttribute("user")).getId());
        } else {
            logger.info("Get number of page from request:");
            int page = Integer.parseInt(request.getParameter("page"));
            logger.info("page = " + page);
            pagination(request, page, ((User) request.getSession().getAttribute("user")).getId());
        }

        logger.debug("PaginationCommand finished" + System.lineSeparator());
        return Pages.RESULT;
    }

    public static void pagination(HttpServletRequest request, int page, int userId) {
        List<Result> userResults = UserDAO.getUserResults(userId, (page - 1) * 5);
        request.setAttribute("userResults", userResults);
        logger.info("get number of pages");
        float pagesAmount = UserDAO.getUsersCount(userId);

        request.setAttribute("pagesAmount", Math.ceil(pagesAmount / 5));
    }
}