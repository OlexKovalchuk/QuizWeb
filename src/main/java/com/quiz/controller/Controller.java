package com.quiz.controller;

import com.quiz.controller.command.Command;
import com.quiz.controller.command.CommandHolder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/home"})
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String commandName = request.getParameter("command");
        Command command = CommandHolder.get(commandName);
        if (command == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        String forward = command.execute(request, response);
        request.getRequestDispatcher(forward).forward(request, response);
    }

}
