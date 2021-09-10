//package com.quiz.controller.filters;
//
//import com.quiz.controller.command.Paths;
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//@WebFilter("AuthFilter")
//public class AuthFilter implements Filter {
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        HttpSession session = httpRequest.getSession();
//        boolean isUserEmpty = session.getAttribute("user") == null;
//        boolean isHomePage = httpRequest.getRequestURI().startsWith(Paths.HOMEPAGE);
//        boolean isLoginPage = httpRequest.getRequestURI().startsWith(Paths.LOGGING);
//        boolean isRegistrationPage = httpRequest.getRequestURI().startsWith(Paths.REGISTER);
//        if(isUserEmpty && !isHomePage && !isLoginPage && !isRegistrationPage){
//            httpResponse.sendRedirect(Paths.HOMEPAGE);
//        }
//        chain.doFilter(request, response);
//    }
//}
