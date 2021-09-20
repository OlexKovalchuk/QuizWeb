package com.quiz.controller.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.apache.log4j.Logger;

import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class EncodingFilter implements Filter {
    final static Logger logger = Logger.getLogger(EncodingFilter.class);

    public void init(FilterConfig config) throws ServletException {
        logger.debug("EncodingFilter starts:");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    public void destroy() {
        logger.debug("EncodingFilter finished");
    }
}
