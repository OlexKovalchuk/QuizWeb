package com.quiz.DB.dao.impl;

import com.quiz.DB.DAOFactory;
import com.quiz.DB.LogConfigurator;
import com.quiz.DB.MySqlDAOFactory;
import com.quiz.DB.DBConnection;
import com.quiz.DB.dao.interfaces.IQuestionDAO;
import com.quiz.entity.Question;
import com.quiz.exceptions.UnsuccessfulQueryException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.quiz.DB.DBConnection.closeResultSet;

public class QuestionDAO extends AbstractDAO<Question> implements IQuestionDAO {
    private final static Logger logger;

    public QuestionDAO(Connection connection) {
        super(connection);
    }

    @Override
    protected PreparedStatement getSelectEntityByIdStatement() throws SQLException {
        return connection.prepareStatement("Select * from question where id =?");
    }

    @Override
    protected PreparedStatement getSelectAllEntitiesStatement() throws SQLException {
        return connection.prepareStatement("Select * from question");
    }

    @Override
    protected PreparedStatement getInsertEntityStatement() throws SQLException {
        return connection.prepareStatement("Insert Into question ( quiz_id, description) VALUES (?,?)");
    }

    @Override
    protected PreparedStatement getLastInsertIdStatement() throws SQLException {
        return connection.prepareStatement("Select LAST_INSERT_ID() from question ");
    }

    @Override
    protected PreparedStatement getUpdateStatement() throws SQLException {
        return connection.prepareStatement("Update question set  quiz_id=? ,description=? where id=? ");
    }

    @Override
    protected PreparedStatement getDeleteStatement() throws SQLException {
        return connection.prepareStatement("Delete from question where quiz_id =?");
    }

    @Override
    protected int getEntityId(Question entity) {
        return entity.getId();
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Question entity) throws SQLException {
        statement.setInt(1, entity.getTestId());
        statement.setString(2, entity.getDescription());
        statement.setInt(3, entity.getId());
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Question entity) throws SQLException {
        statement.setInt(1, entity.getTestId());
        statement.setString(2, entity.getDescription());
    }

    @Override
    protected Question getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Question question = new Question();
        question.setTestId(resultSet.getInt("quiz_id"));
        question.setDescription(resultSet.getString("description"));
        question.setId(resultSet.getInt("id"));
        return question;
    }

    static {
        logger = LogConfigurator.getLogger(QuizDAO.class);
    }

    public void deleteAllQuestionsByQuizId(int id) {
        try (PreparedStatement statement = connection.prepareStatement("Delete  from question where quiz_id=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }

    }

    public List<Question> getQuestionsByQuizId(int quizID) throws UnsuccessfulQueryException {
        List<Question> questions = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT description ,id " +
                        "FROM question WHERE quiz_id = ? ;")) {

            statement.setInt(1, quizID);
            resultSet = statement.executeQuery();
            DAOFactory factory = new MySqlDAOFactory();
            AnswerDAO answerDAO = factory.createAnswerDAO(new DBConnection());
            Question question;
            while (resultSet.next()) {
                question = new Question();
                question.setId(resultSet.getInt("id"));
                question.setDescription(resultSet.getString("description"));
                question.setAnswers(answerDAO.getAnswersByQuestionId(question.getId()));
                questions.add(question);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        } finally {
            closeResultSet(resultSet);
        }
        return questions;
    }

    public boolean insertQuestions(List<Question> questions, int quizId) {
        int changes =0;
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO question (quiz_id, " +
                "description) values (?,?)", Statement.RETURN_GENERATED_KEYS)) {
            for (Question question : questions) {
                statement.setInt(1, quizId);
                statement.setString(2, question.getDescription());
                changes= statement.executeUpdate();
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        question.setId(generatedKeys.getInt(1));
                    }
                }
                DAOFactory factory = new MySqlDAOFactory();
                try (DBConnection conn = factory.createConnection()) {
                    AnswerDAO answerDAO = factory.createAnswerDAO(conn);
                    answerDAO.insertAnswers(question.getAnswers(), question.getId());
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        return changes > 0;
    }

    public void addQuestion(Question question) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO question (quiz_id, " +
                "description) values (?,?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, question.getTestId());
            statement.setString(2, question.getDescription());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    question.setId(generatedKeys.getInt(1));
                }
            }
            DAOFactory factory = new MySqlDAOFactory();
            try (DBConnection conn = factory.createConnection()) {
                AnswerDAO answerDAO = factory.createAnswerDAO(conn);
                answerDAO.insertAnswers(question.getAnswers(), question.getId());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
    }
}