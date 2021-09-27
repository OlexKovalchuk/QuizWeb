package com.quiz.service;

import com.quiz.DB.*;
import com.quiz.DB.dao.impl.QuestionDAO;
import com.quiz.DB.dao.impl.QuizDAO;
import com.quiz.entity.Quiz;
import org.apache.log4j.Logger;

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
        try (DBConnection conn = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(conn);
            QuestionDAO questionDAO = factory.createQuestionDAO(conn);
            Quiz quiz = quizDAO.findById(id);
            quiz.setQuestions(questionDAO.getQuestionsByQuizId(id));
            return quiz;
        }
    }

    public boolean updateQuizInfoById(Quiz quiz) {
        try (DBConnection conn = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(conn);
        return    quizDAO.update(quiz);
        }
    }


    public boolean insertQuiz(Quiz quiz) {
        try (DBConnection conn = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(conn);
           return quizDAO.insertQuiz(quiz);
        }
    }

    public boolean deleteQuiz(int id) {
        try (DBConnection conn = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(conn);
         return   quizDAO.delete(id);
        }
    }

    public int getQuizCount(String topic) {
        try (DBConnection conn = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(conn);
            return quizDAO.getQuizCount(topic);
        }
    }

    public List<Quiz> getAllQuizWithPagination(int page, String topic) {
        try (DBConnection conn = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(conn);
            return quizDAO.getAllQuizWithPagination((page - 1) * 4, topic, "", "");
        }
    }

    public void editQuiz(int id, Quiz quiz) {
        try (DBConnection conn = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(conn);
            quizDAO.insertQuiz(quiz);
        }
    }

    @Sort(type = "order by", param = "quiz.create_date")
    public List<Quiz> getAllQuizWithPaginationByDate(int page, String topic) {
        try (DBConnection conn = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(conn);
            Sort annotation = (Sort) QuizService.class.getMethod("getAllQuizWithPaginationByDate", int.class,
                    String.class).getAnnotation(Sort.class);
            return quizDAO.getAllQuizWithPagination((page - 1) * 4, topic, annotation.type(), annotation.param());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Sort(type = "order by", param = "t.name")
    public List<Quiz> getAllQuizWithPaginationByTopic(int page, String topic) {
        try (DBConnection conn = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(conn);
            try {
                Sort annotation =
                        (Sort) QuizService.class.getMethod("getAllQuizWithPaginationByTopic", int.class,
                                String.class).getAnnotation(Sort.class);
                return quizDAO.getAllQuizWithPagination((page - 1) * 4, topic, annotation.type(), annotation.param());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }
    }

    @Sort(type = "order by", param = "quiz.duration")
    public List<Quiz> getAllQuizWithPaginationByDuration(int page, String topic) {
        try (DBConnection conn = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(conn);
            try {
                Sort annotation =
                        (Sort) QuizService.class.getMethod("getAllQuizWithPaginationByDuration", int.class,
                                String.class).getAnnotation(Sort.class);
                return quizDAO.getAllQuizWithPagination((page - 1) * 4, topic, annotation.type(), annotation.param());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }
    }

    @Sort(type = "order by", param = "quiz.difficult")
    public List<Quiz> getAllQuizWithPaginationByDifficult(int page, String topic) {
        try (DBConnection conn = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(conn);
            try {
                Sort annotation =
                        (Sort) QuizService.class.getMethod("getAllQuizWithPaginationByDifficult", int.class,
                                String.class).getAnnotation(Sort.class);
                return quizDAO.getAllQuizWithPagination((page - 1) * 4, topic, annotation.type(), annotation.param());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }
    }

    @Sort(type = "order by", param = "count")//count
    public List<Quiz> getAllQuizWithPaginationByQuestion(int page, String topic) {
        try (DBConnection conn = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(conn);
            try {
                Sort annotation =
                        (Sort) QuizService.class.getMethod("getAllQuizWithPaginationByQuestion", int.class,
                                String.class).getAnnotation(Sort.class);
                return quizDAO.getAllQuizWithPagination((page - 1) * 4, topic, annotation.type(), annotation.param());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }
    }
}