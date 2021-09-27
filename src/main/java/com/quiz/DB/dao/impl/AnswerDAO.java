package com.quiz.DB.dao.impl;

import com.quiz.DB.LogConfigurator;
import com.quiz.DB.dao.interfaces.IAnswerDAO;
import com.quiz.entity.Answer;
import com.quiz.exceptions.UnsuccessfulQueryException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.quiz.DB.DBConnection.closeResultSet;

public class AnswerDAO extends AbstractDAO<Answer> implements IAnswerDAO {
    private final static Logger logger;

    public AnswerDAO(Connection connection) {
        super(connection);
    }

    @Override
    protected PreparedStatement getSelectEntityByIdStatement() throws SQLException {
        return connection.prepareStatement("Select * from answer where answer.id =?");
    }

    @Override
    protected PreparedStatement getSelectAllEntitiesStatement() throws SQLException {
        return connection.prepareStatement("Select * from answer ");

    }

    @Override
    protected PreparedStatement getInsertEntityStatement() throws SQLException {
        return connection.prepareStatement("Insert Into answer  (answer.question_id,answer.description,answer.answer) values (?,?,?)");

    }

    @Override
    protected PreparedStatement getLastInsertIdStatement() throws SQLException {
        return connection.prepareStatement("Select LAST_INSERT_ID() from answer");

    }

    @Override
    protected PreparedStatement getUpdateStatement() throws SQLException {
        return connection.prepareStatement("Update  answer set answer.question_id=? ,answer.description=?, answer.answer=? where answer.id=?  ");
    }

    @Override
    protected PreparedStatement getDeleteStatement() throws SQLException {
        return connection.prepareStatement("DELETE from answer where answer.id=?  ");
    }

    @Override
    protected int getEntityId(Answer entity) {
        return entity.getId();
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Answer entity) throws SQLException {
        statement.setInt(1, entity.getQuestionId());
        statement.setString(2, entity.getDescription());
        statement.setInt(3, entity.getAnswer());
        statement.setInt(7, entity.getId());
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Answer entity) throws SQLException {
        statement.setInt(1, entity.getQuestionId());
        statement.setString(2, entity.getDescription());
        statement.setInt(3, entity.getAnswer());
    }

    @Override
    protected Answer getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Answer answer = new Answer();
        answer.setQuestionId(resultSet.getInt("question_id"));
        answer.setDescription(resultSet.getString("description"));
        answer.setAnswer(resultSet.getInt("answer"));
        return  answer;
    }

    static {
        logger = LogConfigurator.getLogger(QuizDAO.class);
    }

    public List<Answer> getAnswersByQuestionId(int questionId) throws UnsuccessfulQueryException {
        List<Answer> answers = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT description,answer " +
                        "FROM answer WHERE question_id = ? ;")) {

            statement.setInt(1, questionId);
            resultSet = statement.executeQuery();
            Answer answer;
            while (resultSet.next()) {
                answer = new Answer();
                answer.setAnswer(resultSet.getInt("answer"));
                answer.setDescription(resultSet.getString("description"));
                answers.add(answer);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        } finally {
            closeResultSet(resultSet);
        }
        return answers;
    }

    public boolean insertAnswers(List<Answer> answers, int questionId) {
        int[] changes;
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO answer (question_id, " +
                "description,answer) values (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            for (Answer answer : answers) {
                statement.setInt(1, questionId);
                statement.setString(2, answer.getDescription());
                statement.setInt(3, answer.getAnswer());
                statement.addBatch();
            }
           changes= statement.executeBatch();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        return changes[0]>0;
    }
}