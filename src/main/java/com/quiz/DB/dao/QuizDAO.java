package com.quiz.DB.dao;

import com.quiz.DB.DAOFactory;
import com.quiz.DB.LogConfigurator;
import com.quiz.DB.MySqlDAOFactory;
import com.quiz.DB.SqlConnection;
import com.quiz.entity.Quiz;
import com.quiz.exceptions.UnsuccessfulQueryException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.quiz.DB.SqlConnection.closeResultSet;

public class QuizDAO {
    private final static Logger logger;
    private final Connection connection;

    public QuizDAO(Connection connection) {
        this.connection = connection;

    }

    static {
        logger = LogConfigurator.getLogger(QuizDAO.class);
    }

    public int getQuizCount() {
        int count = 0;
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) as count from quiz")) {
            resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getInt("count");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        } finally {
            closeResultSet(resultSet);
        }
        return count;
    }

    public Quiz getQuiz(int id) {
        ResultSet resultSet = null;
        Quiz quiz = new Quiz();
        try (PreparedStatement statement = connection.prepareStatement("SELECT quiz.id, quiz.header, quiz.duration  " +
                "From  quiz where" +
                " quiz.id = ?;")) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();
            quiz.setHeader(resultSet.getString("header"));
            quiz.setId(resultSet.getInt("id"));
            quiz.setDuration(resultSet.getInt("duration"));
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        } finally {
            closeResultSet(resultSet);
        }
        return quiz;
    }

    private void getSingleQuiz(ResultSet resultSet, Quiz quiz) throws SQLException {
        quiz.setHeader(resultSet.getString("header"));
        quiz.setId(resultSet.getInt("id"));
        quiz.setDifficult(resultSet.getString("difficult"));
        quiz.setDuration(resultSet.getInt("duration"));
        quiz.setDescription(resultSet.getString("description"));
        quiz.setCreateDate(resultSet.getDate("create_date"));
        quiz.setTopicName(resultSet.getString("name"));
        quiz.setCount(resultSet.getInt("count"));
    }

    public List<Quiz> getAllQuizWithPagination(int offset, String type, String param) {
        ResultSet resultSet = null;
        String sql = "SELECT quiz.id, quiz.header,quiz.difficult," +
                "quiz.description, quiz.create_date, quiz.duration,t.name as name, COUNT(quiz.id=q.quiz_id) as count " +
                "FROM quiz  " +
                "JOIN topic t " +
                "on t.id = quiz.topic_id INNER JOIN question q on q.quiz_id = quiz.id  group  by  " + (param.equals(
                "count") ? " " : param) + (param.length() > 0 && !param.equals("count") ? "," : "") + " quiz.id " + type + " " + param + "   limit 4 " +
                "offset" +
                " ?";
        List<Quiz> quizzes = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, offset);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            Quiz quiz;
            while (resultSet.next()) {
                quiz = new Quiz();
                getSingleQuiz(resultSet, quiz);
                quizzes.add(quiz);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        } finally {
            closeResultSet(resultSet);
        }

        return quizzes;
    }


    public void insertQuiz(Quiz quiz) {
        try (PreparedStatement statement = connection.prepareStatement("insert into quiz(description,difficult," +
                "duration,header,create_date,topic_id" +
                ") values(?,?,?,?,NOW(),?)", Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, quiz.getDescription());
            statement.setString(2, quiz.getDifficult());
            statement.setDouble(3, quiz.getDuration());
            statement.setString(4, quiz.getHeader());
            statement.setInt(5, quiz.getTopicId());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    quiz.setId(generatedKeys.getInt(1));
                }
            }
            DAOFactory factory = new MySqlDAOFactory();
            QuestionDAO questionDAO = factory.createQuestionDAO(new SqlConnection());
            questionDAO.insertQuestions(quiz.getQuestions(), quiz.getId());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
    }

//    public static void deleteQuestion(int id) {
//        try (Connection connection = DBManager.getConnection();
//             PreparedStatement statement = connection.prepareStatement("DELETE FROM  question WHERE id=?")) {
//            statement.setInt(1, id);
//            statement.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void deleteQuiz(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM  quiz WHERE id=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}