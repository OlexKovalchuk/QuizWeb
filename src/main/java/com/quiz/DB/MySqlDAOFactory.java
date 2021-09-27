package com.quiz.DB;

import com.quiz.DB.dao.impl.*;

/**
 * The concrete implementation of the DAOFactory
 * for the MySQL relational database
 */
public class MySqlDAOFactory implements DAOFactory {

    @Override
    public UserDAO createUserDAO(DBConnection connection) {
        return new UserDAO(connection.getConnection());
    }

    @Override
    public RoleDAO createRoleDAO(DBConnection connection) {
        return new RoleDAO(connection.getConnection());
    }

    @Override
    public TopicDAO createTopicDAO(DBConnection connection) {
        return new TopicDAO(connection.getConnection());
    }

    @Override
    public QuizDAO createQuizDAO(DBConnection connection) {
        return new QuizDAO(connection.getConnection());
    }

    @Override
    public QuestionDAO createQuestionDAO(DBConnection connection) {
        return new QuestionDAO(connection.getConnection());
    }

    @Override
    public AnswerDAO createAnswerDAO(DBConnection connection) {
        return new AnswerDAO(connection.getConnection());
    }

    @Override
    public ResultDAO createResultDAO(DBConnection connection) {
        return new ResultDAO(connection.getConnection());
    }

    @Override
    public DBConnection createConnection() {
        return new DBConnection();
    }
}