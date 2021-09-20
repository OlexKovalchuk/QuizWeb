package com.quiz.service;

import com.quiz.DB.*;
import com.quiz.DB.dao.QuestionDAO;
import com.quiz.DB.dao.QuizDAO;
import com.quiz.entity.Quiz;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class QuizService {
    private final DAOFactory factory;
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(QuizService.class);
    }

    public QuizService() {
        this.factory = new MySqlDAOFactory();
    }

    public Quiz getQuiz(int id) {
        try (SqlConnection conn = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(conn);
            QuestionDAO questionDAO = factory.createQuestionDAO(conn);
            Quiz quiz = quizDAO.getQuiz(id);
            quiz.setQuestions(questionDAO.getQuestionsByQuizId(id));
            return quiz;
        }
    }


    public void insertQuiz(Quiz quiz) {
        try (SqlConnection conn = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(conn);
            quizDAO.insertQuiz(quiz);
        }
    }

    public void deleteQuiz(int id) {
        try (SqlConnection conn = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(conn);
            quizDAO.deleteQuiz(id);
        }
    }

    public int getQuizCount() {
        try (SqlConnection conn = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(conn);
            return quizDAO.getQuizCount();
        }
    }

    public List<Quiz> getAllQuizWithPagination(int page) {
        try (SqlConnection conn = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(conn);
            return quizDAO.getAllQuizWithPagination((page - 1) * 4, "", "");
        }
    }

    public void editQuiz(int id,Quiz quiz){
        try (SqlConnection conn = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(conn);
            quizDAO.deleteQuiz(id);
            quizDAO.insertQuiz(quiz);
        }
    }
    @Sort(type = "order by", param = "quiz.create_date")
    public List<Quiz> getAllQuizWithPaginationByDate(int page) {
        try (SqlConnection conn = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(conn);
            Method m = QuizService.class.getMethod("getAllQuizWithPaginationByDate", int.class);
            Sort annotation = m.getAnnotation(Sort.class);
            System.out.println(annotation.param());
            return quizDAO.getAllQuizWithPagination((page - 1) * 4, annotation.type(), annotation.param());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Sort(type = "order by", param = "t.name")
    public List<Quiz> getAllQuizWithPaginationByTopic(int page) {
        try (SqlConnection conn = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(conn);
            try {
                Sort sortAnnotation =
                        (Sort) QuizService.class.getMethod("getAllQuizWithPaginationByTopic", int.class).getAnnotation(Sort.class);
                return quizDAO.getAllQuizWithPagination((page - 1) * 4, sortAnnotation.type(), sortAnnotation.param());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }
    }

    @Sort(type = "order by", param = "quiz.duration")
    public List<Quiz> getAllQuizWithPaginationByDuration(int page) {
        try (SqlConnection conn = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(conn);
            try {
                Sort sortAnnotation =
                        (Sort) QuizService.class.getMethod("getAllQuizWithPaginationByDuration", int.class).getAnnotation(Sort.class);
                return quizDAO.getAllQuizWithPagination((page - 1) *4, sortAnnotation.type(), sortAnnotation.param());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }
    }

    @Sort(type = "order by", param = "quiz.difficult")
    public List<Quiz> getAllQuizWithPaginationByDifficult(int page) {
        try (SqlConnection conn = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(conn);
            try {
                Sort sortAnnotation =
                        (Sort) QuizService.class.getMethod("getAllQuizWithPaginationByDifficult", int.class).getAnnotation(Sort.class);
                return quizDAO.getAllQuizWithPagination((page - 1) * 4, sortAnnotation.type(), sortAnnotation.param());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }
    }

    @Sort(type = "order by", param = "count")//count
    public List<Quiz> getAllQuizWithPaginationByQuestion(int page) {
        try (SqlConnection conn = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(conn);
            try {
                Sort sortAnnotation =
                        (Sort) QuizService.class.getMethod("getAllQuizWithPaginationByQuestion", int.class).getAnnotation(Sort.class);
                return quizDAO.getAllQuizWithPagination((page - 1) * 4, sortAnnotation.type(), sortAnnotation.param());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }
    }
}