package com.quiz.controller.command;

import com.quiz.controller.utils.WebPath;
import com.quiz.entity.Answer;
import com.quiz.entity.Question;
import com.quiz.entity.Quiz;
import com.quiz.service.QuizService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public class SaveQuizCommand implements Command {
    @Override
    public WebPath execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        Quiz quiz = new Quiz();
        QuizService quizService = new QuizService();
        List<Question> questions = new ArrayList<>();
        quiz.setQuestions(questions);
        Enumeration<String> params = request.getParameterNames();
        quiz.setHeader(request.getParameter(params.nextElement()));
        quiz.setTopicId(Integer.parseInt(request.getParameter(params.nextElement())));
        quiz.setDifficult(request.getParameter(params.nextElement()));
        quiz.setDuration(Integer.parseInt(request.getParameter(params.nextElement())));
        quiz.setDescription(request.getParameter(params.nextElement()));
        String param = params.nextElement();
        while (params.hasMoreElements()) {
            if (param.charAt(0) == 'd') {
                Question question = new Question();
                question.setDescription(request.getParameter(param));
                List<Answer> answers = new ArrayList<>();
                question.setAnswers(answers);
                List<String> getParameter = null;
                while (params.hasMoreElements()) {
                    param = params.nextElement();
                    if (param.charAt(0) != 'd'){
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
                    }else {
                        break;
                    }
                }
                questions.add(question);
            }
        }

        quizService.insertQuiz(quiz);
        return new WebPath(WebPath.WebPageEnum.HOME.getPath(), WebPath.DispatchType.REDIRECT);

    }
}
