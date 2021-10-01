package com.quiz.web.filters;

import com.quiz.web.command.ErrorCommand;
import com.quiz.web.utils.ErrorMessage;
import com.quiz.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

;

/**
 * Admin web filter doesn't give access to all functions to user that hasn't ADMIN ROLE
 */
@WebFilter(urlPatterns = {"/create","/edit","/editQuizQuestions","/editQuizInfo","/delete","/user/edit","/user/delete","/profile/users","/profile/users/edit","/profile/users/delete"})
public class AdminAuthorizationFilter implements Filter {

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final HttpSession session = request.getSession(false);

        if(isAdmin(session)){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setRedirectUrl(request.getContextPath()+"/logout");
            errorMessage.setType("Ooops");
            errorMessage.setHeader("You aren't admin");
            errorMessage.setDescription("Only admin can get this page");
            errorMessage.setButtonName("Logout");
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher(new ErrorCommand().execute(request,response).getName()).forward(request, response);
        }

    }
    boolean isAdmin(HttpSession session) {
        return (session!=null
                && session.getAttribute("user")!=null &&((User)session.getAttribute("user")).getRole().getRoleName().equals("admin"));
    }
}