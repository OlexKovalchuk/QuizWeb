package com.quiz.web.filters;

import com.quiz.entity.User;
import com.quiz.web.command.ErrorCommand;
import com.quiz.web.utils.ErrorMessage;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Authorization filter doesn't give access to basic functions to user that hasn't logged in
 */
@WebFilter(urlPatterns = {"/profile","/edit","/create", "/profile/results", "/start","/editQuizInfo" ,"/editQuizQuestions","/user/delete","/finish","/delete","/profile/users","/profile/edit","/user/edit"})
public class AuthorizationFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final HttpSession session = request.getSession(false);
        if (isLoggedIn(session)) {
            if (((User) session.getAttribute("user")).getBlock() == 0) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setRedirectUrl(request.getContextPath() + "/home");
                errorMessage.setType("Ooops");
                errorMessage.setHeader("You are blocked by admin");
                errorMessage.setDescription("If you had worked harder, you wouldn't have been blocked");
                errorMessage.setButtonName("Go to home page");
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher(new ErrorCommand().execute(request, response).getName()).forward(request
                        , response);
            }
        } else {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setRedirectUrl(request.getContextPath() + "/login");
            errorMessage.setType("Ooops");
            errorMessage.setHeader("Please login");
            errorMessage.setDescription("To access all functions you should be logged in");
            errorMessage.setButtonName("Go to login page");
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher(new ErrorCommand().execute(request, response).getName()).forward(request,
                    response);
        }
    }

    boolean isLoggedIn(HttpSession session) {
        return (session != null
                && session.getAttribute("user") != null);
    }
}