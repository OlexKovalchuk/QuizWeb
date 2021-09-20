package com.quiz.DB;

import com.quiz.DB.dao.*;

/**
 * Basic interface for implementing DAO factories,
 * so that switching between databases would be much easier
 */
public interface DAOFactory {
    UserDAO createUserDAO(SqlConnection connection);
    RoleDAO createRoleDAO(SqlConnection connection);
    TopicDAO createTopicDAO(SqlConnection connection);
    QuizDAO createQuizDAO(SqlConnection connection);
    QuestionDAO createQuestionDAO(SqlConnection connection);
    AnswerDAO createAnswerDAO(SqlConnection connection);
    ResultDAO createResultDAO(SqlConnection connection);
    SqlConnection createConnection();
}
