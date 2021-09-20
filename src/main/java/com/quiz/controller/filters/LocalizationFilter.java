package com.quiz.controller.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Locale;

@WebFilter( urlPatterns = {"/*"})
public class LocalizationFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getParameter("lang") != null) {
            req.getSession().setAttribute("lang", req.getParameter("lang"));
            Locale.setDefault(new Locale(req.getParameter("lang")));
        }
        chain.doFilter(request, response);
    }
}