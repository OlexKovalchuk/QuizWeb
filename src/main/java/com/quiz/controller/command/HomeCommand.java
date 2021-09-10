package com.quiz.controller.command;

import com.quiz.DB.dao.TestDAO;
import com.quiz.controller.utils.Pages;
import com.quiz.entity.Test;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class HomeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {


        List<Test> testList = TestDAO.getAllTests();
        request.setAttribute("tests", testList);

        return Pages.HOME;
    }
}
