package com.quiz.DB.dao;

import com.quiz.DB.DAOFactory;
import com.quiz.DB.LogConfigurator;
import com.quiz.DB.MySqlDAOFactory;
import com.quiz.DB.SqlConnection;
import com.quiz.entity.Question;
import com.quiz.exceptions.UnsuccessfulQueryException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.quiz.DB.SqlConnection.closeResultSet;

public class QuestionDAO {
    private final static Logger logger;
    private Connection connection;

    public QuestionDAO(Connection connection) {
        this.connection = connection;

    }

    static {
        logger = LogConfigurator.getLogger(QuizDAO.class);
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
            AnswerDAO answerDAO = factory.createAnswerDAO(new SqlConnection());
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

    public void insertQuestions(List<Question> questions, int quizId) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO question (quiz_id, " +
                "description) values (?,?)", Statement.RETURN_GENERATED_KEYS)) {
            for (Question question : questions) {
                statement.setInt(1, quizId);
                statement.setString(2, question.getDescription());
                statement.executeUpdate();
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        question.setId(generatedKeys.getInt(1));
                    }
                }
                DAOFactory factory = new MySqlDAOFactory();
                try ( SqlConnection conn = factory.createConnection()) {
                    AnswerDAO answerDAO = factory.createAnswerDAO(conn);
                    answerDAO.insertAnswers(question.getAnswers(), question.getId());
                }
            }
            statement.executeBatch();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }

    }
}
