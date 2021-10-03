package com.quiz.service;

import com.quiz.DB.DAOFactory;
import com.quiz.DB.DBConnection;
import com.quiz.DB.MySqlDAOFactory;
import com.quiz.DB.dao.impl.QuestionDAO;
import com.quiz.DB.dao.impl.QuizDAO;
import com.quiz.entity.Quiz;
import com.quiz.exceptions.UnsuccessfulQueryException;
import com.quiz.web.utils.Pageable;
import org.apache.log4j.Logger;

import java.util.List;

public class QuizService {
    private final DAOFactory factory;
    public static final Logger logger = Logger.getLogger(QuizService.class);


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
        try (DBConnection connection = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(connection);
            try {
                connection.setAutoCommit(false);
                quizDAO.update(quiz);
                connection.commit();
            } catch (UnsuccessfulQueryException e) {
                logger.error(e.getMessage());
                connection.rollback();
                return false;
            }
            return true;
        }
    }


    public boolean insertQuiz(Quiz quiz) {
        try (DBConnection connection = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(connection);
              return   quizDAO.insertQuiz(quiz);
        }
    }

    public boolean deleteQuiz(int id) {
        try (DBConnection connection = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(connection);
            try {
                connection.setAutoCommit(false);
                if (quizDAO.isQuizHasResults(id)) {
                     quizDAO.archiveQuiz(id);
                } else {
                     quizDAO.delete(id);
                }
                connection.commit();
            } catch (UnsuccessfulQueryException e) {
                logger.error(e.getMessage());
                connection.rollback();
                return false;
            }
            return true;
        }
    }

    public int getQuizCount(int topicId) {
        try (DBConnection conn = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(conn);
            return quizDAO.getQuizCount(topicId);
        }
    }

    public List<Quiz> getAllQuizzes(int topicId, Pageable pageable) {
        try (DBConnection conn = factory.createConnection()) {
            QuizDAO quizDAO = factory.createQuizDAO(conn);
            return quizDAO.getAllQuizzes(topicId, pageable);
        }
    }

}