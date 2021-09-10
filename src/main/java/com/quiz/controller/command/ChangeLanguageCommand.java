package com.quiz.controller.command;

import com.quiz.controller.utils.Pages;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class ChangeLanguageCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute("language");
        if (language.equals("en")) {
            session.setAttribute("language", "ua");
        }
        else {
            session.setAttribute("language","en");
        }

        String currentPage = (String)session.getAttribute("currentPage");
        if (currentPage==null) {
            return Pages.HOME;
        }

        return currentPage;
    }
}
