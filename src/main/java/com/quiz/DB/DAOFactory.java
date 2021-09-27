package com.quiz.DB;

import com.quiz.DB.dao.impl.*;


public interface DAOFactory {
    UserDAO createUserDAO(DBConnection connection);
    RoleDAO createRoleDAO(DBConnection connection);
    TopicDAO createTopicDAO(DBConnection connection);
    QuizDAO createQuizDAO(DBConnection connection);
    QuestionDAO createQuestionDAO(DBConnection connection);
    AnswerDAO createAnswerDAO(DBConnection connection);
    ResultDAO createResultDAO(DBConnection connection);
    DBConnection createConnection();
}