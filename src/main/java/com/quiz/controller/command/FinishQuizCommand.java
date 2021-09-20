package com.quiz.controller.command;

import com.quiz.controller.utils.WebPath;
import com.quiz.entity.Question;
import com.quiz.entity.Quiz;
import com.quiz.entity.User;
import com.quiz.service.QuizService;
import com.quiz.service.ResultService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class FinishQuizCommand implements Command {
    @Override
    public WebPath execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        int score;
        double correct = 0;
        QuizService quizService = new QuizService();
        ResultService resultService = new ResultService();
        Quiz quiz = quizService.getQuiz(Integer.parseInt(request.getParameter("id")));
        List<Question> questionList = quiz.getQuestions();
        for (int i = 0; i < questionList.size(); i++) {
            if (request.getParameterValues("question-" + i + "-answers") != null) {
                if (questionList.get(i).isCorrect(request.getParameterValues("question-" + i + "-answers"))) {
                    correct++;
                }
            }
        }
        score = (int) (100 * correct / questionList.size());
        resultService.updateResult(score, ((User) request.getSession().getAttribute("user")).getId(), quiz.getId(),
                quiz.getDuration());
        return new WebPath(WebPath.WebPageEnum.QUIZ_END.getPath(), WebPath.DispatchType.REDIRECT);
    }
}
