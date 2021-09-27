package com.quiz.service;

import com.quiz.DB.DAOFactory;
import com.quiz.DB.LogConfigurator;
import com.quiz.DB.MySqlDAOFactory;
import com.quiz.DB.DBConnection;
import com.quiz.DB.dao.impl.QuestionDAO;
import com.quiz.entity.Question;
import org.apache.log4j.Logger;

import java.util.List;

public class QuestionService {
    private final DAOFactory factory;
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(QuestionService.class);
    }


    public QuestionService() {
        this.factory = new MySqlDAOFactory();
    }


    public boolean addQuestion(Question question) {
        try (DBConnection conn = factory.createConnection()) {
            QuestionDAO questionDAO = factory.createQuestionDAO(conn);
            return questionDAO.create(question);
        }
    }

    public boolean updateQuizQuestionsById(List<Question> questions,int id) {
        try (DBConnection conn = factory.createConnection()) {
            QuestionDAO questionDAO = factory.createQuestionDAO(conn);
            questionDAO.delete(id);
           return questionDAO.insertQuestions(questions,id);
        }
    }
}