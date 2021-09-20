package com.quiz.controller.filters;

import com.quiz.DB.LogConfigurator;
import com.quiz.controller.command.ErrorCommand;
import com.quiz.controller.utils.ErrorMessage;
import com.quiz.entity.User;
import com.quiz.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;

@WebFilter(urlPatterns = {"/profile", "/list", "/profile/results", "/start", "/finish"})
public class AuthorizationFilter implements Filter {
    private static Logger logger;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger = LogConfigurator.getLogger(UserService.class);
    }

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
