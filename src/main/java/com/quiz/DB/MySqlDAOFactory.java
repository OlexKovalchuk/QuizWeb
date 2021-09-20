package com.quiz.DB;

import com.quiz.DB.dao.*;

/**
 * The concrete implementation of the DAOFactory
 * for the MySQL relational database
 */
public class MySqlDAOFactory implements DAOFactory {

    @Override
    public UserDAO createUserDAO(SqlConnection sqlConnection) {
        return new UserDAO(sqlConnection.getConnection());
    }

    @Override
    public RoleDAO createRoleDAO(SqlConnection connection) {
        return new RoleDAO(connection.getConnection());
    }

    @Override
    public TopicDAO createTopicDAO(SqlConnection connection) {
        return new TopicDAO(connection.getConnection());
    }

    @Override
    public QuizDAO createQuizDAO(SqlConnection connection) {
        return new QuizDAO(connection.getConnection());
    }

    @Override
    public QuestionDAO createQuestionDAO(SqlConnection connection) {
        return new QuestionDAO(connection.getConnection());
    }

    @Override
    public AnswerDAO createAnswerDAO(SqlConnection connection) {
        return new AnswerDAO(connection.getConnection());
    }

    @Override
    public ResultDAO createResultDAO(SqlConnection connection) {
        return new ResultDAO(connection.getConnection());
    }

    @Override
    public SqlConnection createConnection() {
        return new SqlConnection();
    }
}
