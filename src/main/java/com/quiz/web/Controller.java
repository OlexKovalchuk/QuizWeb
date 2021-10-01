package com.quiz.web;

import com.quiz.web.command.Command;
import com.quiz.web.command.CommandFactory;
import com.quiz.web.utils.WebPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Servlet that realize FrontController pattern
 */
@WebServlet(urlPatterns = {"/"})
public class Controller extends HttpServlet {
    private final static Logger logger = Logger.getLogger(Controller.class);



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        processRequest(request, response);
    }

    @Override
    public void init() throws ServletException {
        String contextPath = getServletContext().getRealPath("/");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        CommandFactory factory = new CommandFactory(request);
        Command command = factory.defineCommand();
        WebPath page = command.execute(request, response);
        if (page.getDispatchType().equals(WebPath.DispatchType.REDIRECT)) {
            response.sendRedirect(request.getContextPath() + page.getName());
        } else if (page.getDispatchType().equals(WebPath.DispatchType.FORWARD)) {
            request.getRequestDispatcher(page.getName()).forward(request, response);
        }
    }
}