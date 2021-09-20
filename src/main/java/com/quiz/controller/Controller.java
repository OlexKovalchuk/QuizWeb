package com.quiz.controller;

import com.quiz.DB.LogConfigurator;
import com.quiz.controller.command.Command;
import com.quiz.controller.command.CommandFactory;
import com.quiz.controller.utils.WebPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class Controller extends HttpServlet {
    private Logger logger;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        processRequest(request, response);
    }

    @Override
    public void init() throws ServletException {
        String contextPath = getServletContext().getRealPath("/");

        logger = LogConfigurator.getLogger(contextPath, this.getClass());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        CommandFactory factory = new CommandFactory(request, response);
        Command command = factory.defineCommand();
        WebPath page = command.execute(request, response);
        if (page.getDispatchType().equals(WebPath.DispatchType.REDIRECT)) {
            response.sendRedirect(request.getContextPath() + page.getName());
        } else {
            request.getRequestDispatcher(page.getName()).forward(request, response);
        }
    }
}

