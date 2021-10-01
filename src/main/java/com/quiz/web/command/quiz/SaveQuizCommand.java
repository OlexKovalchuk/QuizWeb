package com.quiz.web.command.quiz;

import com.quiz.entity.Question;
import com.quiz.entity.Quiz;
import com.quiz.service.QuizService;
import com.quiz.web.command.Command;
import com.quiz.web.utils.WebPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class SaveQuizCommand implements Command {
    @Override
    public WebPath execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        QuizService quizService = new QuizService();
        List<Question> questions = new ArrayList<>();
        Enumeration<String> params = request.getParameterNames();
        Quiz quiz = new Quiz.Builder()
                .questions(questions)
                .header(request.getParameter(params.nextElement()))
                .topicId(Integer.parseInt(request.getParameter(params.nextElement())))
                .difficult(request.getParameter(params.nextElement()))
                .duration(Integer.parseInt(request.getParameter(params.nextElement())))
                .description(request.getParameter(params.nextElement()))
                .build();

        EditQuizQuestionsCommand.getQuizQuestionsAndAnswersFromRequest(request, questions, params);
        quizService.insertQuiz(quiz);
        return new WebPath(WebPath.WebPageEnum.HOME.getPath(), WebPath.DispatchType.REDIRECT);
    }
}