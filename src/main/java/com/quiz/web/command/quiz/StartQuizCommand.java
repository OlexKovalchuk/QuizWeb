package com.quiz.web.command.quiz;

import com.quiz.entity.Quiz;
import com.quiz.entity.Result;
import com.quiz.entity.User;
import com.quiz.service.QuizService;
import com.quiz.service.ResultService;
import com.quiz.web.command.Command;
import com.quiz.web.command.HomeCommand;
import com.quiz.web.utils.Pages;
import com.quiz.web.utils.WebPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class StartQuizCommand implements Command {
    @Override
    public WebPath execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        QuizService quizService = new QuizService();
        ResultService resultService = new ResultService();
        HttpSession session = request.getSession();
        Calendar currentTimeNow = Calendar.getInstance();
        if (session.getAttribute("quizFinishDate") != null) {
            if (currentTimeNow.getTime().compareTo((Date) session.getAttribute("quizFinishDate")) <= 0) {
                request.setAttribute("errorQuizStart", true);
                HomeCommand homeCommand = new HomeCommand();
                return homeCommand.execute(request, response);
            }
        }
        Quiz quiz = quizService.getQuiz(Integer.parseInt(request.getParameter("id")));
        currentTimeNow.add(Calendar.MINUTE, quiz.getDuration());
        Date quizFinishDate = currentTimeNow.getTime();
        session.setAttribute("quizFinishDate", quizFinishDate);
        request.setAttribute("quiz", quiz);
        Result result = new Result();
        result.setTestId(quiz.getId());
        result.setUserId(((User) session.getAttribute("user")).getId());
        result.setScore(0);
        resultService.insertResult(result);
        return new WebPath(Pages.TEST, WebPath.DispatchType.FORWARD);

    }
}