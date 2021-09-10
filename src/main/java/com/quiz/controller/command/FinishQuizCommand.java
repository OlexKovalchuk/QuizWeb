package com.quiz.controller.command;

import com.quiz.DB.dao.TestDAO;
import com.quiz.DB.dao.TopicDAO;
import com.quiz.controller.utils.Pages;
import com.quiz.entity.Question;
import com.quiz.entity.Result;
import com.quiz.entity.Test;
import com.quiz.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class FinishQuizCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        int score;
        double correct = 0;
        Test test = TestDAO.getTest(Integer.parseInt(request.getParameter("id")));
        List<Question> questionList = test.getQuestions();
        for (int i = 0; i < questionList.size(); i++) {
            String answer = request.getParameter("question-" + i + "-answers");
            if (answer != null) {
                if (questionList.get(i).isCorrect(Integer.parseInt(answer)))
                    correct++;
            }

        }
        score = (int) (100 * correct / questionList.size());
        TestDAO.updateResult(score, ((User) request.getSession().getAttribute("user")).getId(), test.getId(),
                (int)  test.getDuration());
        Result result = new Result();
        result.setScore(score);
        result.setTopicName(TopicDAO.getTopicName(test.getTopicId()));
        result.setTestHeader(test.getHeader());
        result.setStartDate(new Timestamp(System.currentTimeMillis()));
        result.setCompleteDate(new Timestamp(System.currentTimeMillis()));
        request.setAttribute("result", result);

        return Pages.QUIZ_RESULT;
    }
}
