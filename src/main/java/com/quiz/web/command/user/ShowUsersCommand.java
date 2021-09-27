package com.quiz.web.command.user;

import com.quiz.web.command.Command;
import com.quiz.web.utils.Pages;
import com.quiz.web.utils.WebPath;
import com.quiz.entity.User;
import com.quiz.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class ShowUsersCommand implements Command {
    @Override
    public WebPath execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        UserService userService = new UserService();

        List<User> users = null;

        String sort = request.getParameter("sort");
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        if (sort != null) {
            switch (sort) {
                case "block":
                    users = userService.getAllUsersWithPaginationByBlock(page);
                    break;
                case "score":
                    users = userService.getAllUsersWithPaginationByScore(page);
                    break;
                case "date":
                    users = userService.getAllUsersWithPaginationByDate(page);
                    break;
                default:
                    users = userService.getAllUsersWithPagination(page);
                    break;
            }
        } else {
            users = userService.getAllUsersWithPagination(page);
        }
        request.setAttribute("sort", sort);
        request.setAttribute("page", page);
        request.setAttribute("pagesCount", (int) Math.ceil(userService.getUsersCount() / 10.0));
        System.out.println(users);
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            for (User user : users) {
                if (user.getId() == id) {
                    user.setBlock(user.getBlock() ^ 1);
                    userService.blockUser(id, user.getBlock());
                    break;
                }
            }
        }
        request.setAttribute("users", users);
        return new WebPath(Pages.LIST_USERS, WebPath.DispatchType.FORWARD);

    }
}