package com.quiz.web.command.user;

import com.quiz.entity.User;
import com.quiz.service.UserService;
import com.quiz.web.command.Command;
import com.quiz.web.utils.Pageable;
import com.quiz.web.utils.Pages;
import com.quiz.web.utils.WebPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class ShowUsersCommand implements Command {
    @Override
    public WebPath execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        UserService userService = new UserService();
        List<User> users = null;
        HttpSession session = request.getSession();
        User activeUser = (User) session.getAttribute("user");
        Pageable pageable = new Pageable.Builder()
                .page(request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1)
                .size(10)
                .sort(request.getParameter("sort") != null ? request.getParameter("sort") : "score")
                .ASC()
                .build();
        users=userService.getAllUsers(activeUser.getId(),pageable);
        request.setAttribute("sort", pageable.getSort());
        request.setAttribute("page", pageable.getPage());
        request.setAttribute("pagesCount", (int) Math.ceil((userService.getUsersCount()-1) / 10.0));
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