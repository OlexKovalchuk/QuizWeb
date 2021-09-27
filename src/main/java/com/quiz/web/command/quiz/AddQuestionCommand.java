package com.quiz.web.command.quiz;

import com.quiz.entity.Answer;
import com.quiz.entity.Question;
import com.quiz.service.QuestionService;
import com.quiz.web.command.Command;
import com.quiz.web.utils.WebPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public class AddQuestionCommand implements Command {
    @Override
    public WebPath execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        Enumeration<String> params = request.getParameterNames();

        String param = params.nextElement();
        Question question = new Question();
        question.setTestId(Integer.parseInt(request.getParameter("quiz_id")));
        question.setDescription(request.getParameter(param));
        List<Answer> answers = new ArrayList<>();
        question.setAnswers(answers);
        List<String> getParameter = null;
        while (params.hasMoreElements()) {
            param = params.nextElement();
            Answer answer = new Answer();
            answer.setAnswer(0);
            if (param.charAt(0) == 'q') {
                getParameter = Arrays.asList(request.getParameterValues(param));
                answer.setAnswer(1);
                param = params.nextElement();
            }
            if (getParameter != null && getParameter.contains(param.substring(7))) {
                answer.setAnswer(1);
            }
            answer.setDescription(request.getParameter(param));
            answers.add(answer);
        }

        QuestionService questionService = new QuestionService();
        questionService.addQuestion(question);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        return new WebPath("", WebPath.DispatchType.STAND);
    }
}