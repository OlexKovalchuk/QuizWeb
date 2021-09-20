package com.quiz.DB.dao;

import com.quiz.DB.LogConfigurator;
import com.quiz.entity.Answer;
import com.quiz.exceptions.UnsuccessfulQueryException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.quiz.DB.SqlConnection.closeResultSet;

public class AnswerDAO {
    private final static Logger logger;
    private Connection connection;

    public AnswerDAO(Connection connection) {

        this.connection = connection;
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

    public void insertAnswers(List<Answer> answers, int questionId) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO answer (question_id, " +
                "description,answer) values (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            for (Answer answer : answers) {
                statement.setInt(1, questionId);
                statement.setString(2, answer.getDescription());
                statement.setInt(3, answer.getAnswer());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }

    }
}
