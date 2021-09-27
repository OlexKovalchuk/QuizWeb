package com.quiz.web.command;

import com.quiz.web.utils.WebPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface Command {

    WebPath execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
