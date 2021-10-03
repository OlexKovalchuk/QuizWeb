package com.quiz.web.command.quiz;

import com.quiz.entity.Result;
import com.quiz.web.command.Command;
import com.quiz.web.utils.WebPath;
import com.quiz.entity.Question;
import com.quiz.entity.Quiz;
import com.quiz.entity.User;
import com.quiz.service.QuizService;
import com.quiz.service.ResultService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FinishQuizCommand implements Command {
    @Override
    public WebPath execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        int score;
        double correct = 0;
        HttpSession session = request.getSession();
        session.removeAttribute("quizFinishDate");
        QuizService quizService = new QuizService();
        ResultService resultService = new ResultService();
        Quiz quiz = quizService.getQuiz(Integer.parseInt(request.getParameter("id")));
        List<List<Integer>> userAnswers = new ArrayList<>();
        List<Question> questionList = quiz.getQuestions();
        for (int i = 0; i < questionList.size(); i++) {
            if (request.getParameterValues("question-" + i + "-answers") != null) {
                userAnswers.add(Arrays.stream(request.getParameterValues("question-" + i + "-answers")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList()));
                if (questionList.get(i).isCorrect(userAnswers.get(i))) {
                    correct++;
                }
            } else {
                userAnswers.add(null);
            }
        }
        score = (int) (100 * correct / questionList.size());

        resultService.updateResult(new Result.Builder()
                .setScore(score)
                .setUserId(((User) session.getAttribute("user")).getId())
                .setTestId(quiz.getId())
                .build());
        return new WebPath(WebPath.WebPageEnum.QUIZ_END.getPath(), WebPath.DispatchType.REDIRECT);
    }
}