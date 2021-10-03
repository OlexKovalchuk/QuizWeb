package com.quiz.DB.dao.impl;

import com.quiz.DB.DAOFactory;
import com.quiz.DB.DBConnection;
import com.quiz.DB.MySqlDAOFactory;
import com.quiz.DB.dao.interfaces.IQuizDAO;
import com.quiz.entity.Quiz;
import com.quiz.exceptions.UnsuccessfulQueryException;
import com.quiz.web.utils.Pageable;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.quiz.DB.DBConnection.closeResultSet;

public class QuizDAO extends AbstractDAO<Quiz> implements IQuizDAO {
    public static final Logger logger =Logger.getLogger(QuizDAO.class);

    public QuizDAO(Connection connection) {
        super(connection);
    }

    @Override
    protected PreparedStatement getSelectEntityByIdStatement() throws SQLException {
        return connection.prepareStatement("Select * from quiz where id =?");
    }

    @Override
    protected PreparedStatement getSelectAllEntitiesStatement() throws SQLException {
        return connection.prepareStatement("select  * from quiz");
    }

    @Override
    protected PreparedStatement getInsertEntityStatement() throws SQLException {
        return connection.prepareStatement("Insert Into  quiz (difficult, duration, topic_id, description, header, " +
                "create_date) values (?,?,?,?,?,NOW())");
    }

    @Override
    protected PreparedStatement getLastInsertIdStatement() throws SQLException {
        return connection.prepareStatement("Select  LAST_INSERT_ID() from quiz");
    }

    @Override
    protected PreparedStatement getUpdateStatement() throws SQLException {
        return connection.prepareStatement("Update quiz set header =? ,description=?,difficult=?, duration=?," +
                "topic_id=? where  quiz.id =?");
    }

    @Override
    protected PreparedStatement getDeleteStatement() throws SQLException {
        return connection.prepareStatement("Delete from quiz where quiz.id=?");
    }

    @Override
    protected int getEntityId(Quiz entity) {
        return entity.getId();
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Quiz entity) throws SQLException {
        statement.setString(1, entity.getHeader());
        statement.setString(2, entity.getDescription());
        statement.setString(3, entity.getDifficult());
        statement.setInt(4, entity.getDuration());
        statement.setInt(5, entity.getTopicId());
        statement.setInt(6, entity.getId());
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Quiz entity) throws SQLException {
        statement.setString(1, entity.getHeader());
        statement.setString(2, entity.getDescription());
        statement.setString(3, entity.getDifficult());
        statement.setInt(4, entity.getDuration());
        statement.setInt(5, entity.getTopicId());
    }

    @Override
    protected Quiz getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new Quiz.Builder()
                .id(resultSet.getInt("id"))
                .header(resultSet.getString("header"))
                .description(resultSet.getString("description"))
                .difficult(resultSet.getString("difficult"))
                .duration(resultSet.getInt("duration"))
                .topicId(resultSet.getInt("topic_id"))
                .createDate(resultSet.getTimestamp("create_date"))
                .build();
    }


    @Override
    public int getQuizCount(int topicId) {
        int count = 0;
        ResultSet resultSet = null;
        try (PreparedStatement statement =
                     connection.prepareStatement("SELECT COUNT(*) as count from quiz q where q.topic_id=? or ?=0")) {
            statement.setInt(1, topicId);
            statement.setInt(2, topicId);
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

    @Override
    public List<Quiz> getAllQuizzes(int topicId, Pageable pageable) {
        ResultSet resultSet = null;
        String sort = pageable.getSort().equals("count") ? "" : (" , " + pageable.getSort());
        String sql = "SELECT quiz.id, quiz.header,quiz.difficult," +
                "quiz.description, quiz.create_date, quiz.duration,t.name as name, COUNT(quiz.id=q.quiz_id) as count " +
                "FROM quiz  " +
                "JOIN topic t " +
                "on t.id = quiz.topic_id INNER JOIN question q on q.quiz_id = quiz.id where (topic_id=? or ?=0) and quiz.archived=0 " +
                "group  by  quiz.id " + sort + pageable.getSortWithOrder() +
                " limit " + pageable.getSize() + " offset " + pageable.getOffset();

        List<Quiz> quizzes = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, topicId);
            statement.setInt(2, topicId);
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

    @Override
    public boolean insertQuiz(Quiz quiz) {
        int changes = 0;
        try (PreparedStatement statement = connection.prepareStatement("insert into quiz(description,difficult," +
                "duration,header,create_date,topic_id" +
                ") values(?,?,?,?,NOW(),?)", Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, quiz.getDescription());
            statement.setString(2, quiz.getDifficult());
            statement.setDouble(3, quiz.getDuration());
            statement.setString(4, quiz.getHeader());
            statement.setInt(5, quiz.getTopicId());
            changes = statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    quiz.setId(generatedKeys.getInt(1));
                }
            }
            DAOFactory factory = new MySqlDAOFactory();
            QuestionDAO questionDAO = factory.createQuestionDAO(new DBConnection());
            questionDAO.insertQuestions(quiz.getQuestions(), quiz.getId());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        return changes > 0;
    }
    @Override
    public boolean isQuizHasResults(int id) {
        ResultSet resultSet = null;
        boolean hasResults = false;
        try (PreparedStatement statement = connection.prepareStatement("SELECT  CASE WHEN COUNT(r.id) > 0 THEN true ELSE " +
                "false " +
                "END FROM result r WHERE r.quiz_id = ?")) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();
            hasResults = resultSet.getBoolean(1);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        } finally {
            closeResultSet(resultSet);
        }
        return hasResults;
    }
    @Override
    public boolean archiveQuiz(int id) {
        int changes =0;
        try (PreparedStatement statement = connection.prepareStatement("Update  quiz q set q.archived =1 where  q.id " +
                "=?")) {
            statement.setInt(1, id);
           changes= statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        return changes>0;
    }
    @Override
    public boolean isHeaderExists(String header) {
        ResultSet resultSet = null;
        boolean isExist = false;
        try (PreparedStatement statement = connection.prepareStatement("SELECT  CASE WHEN COUNT(q.id) > 0 THEN true ELSE " +
                "false " +
                "END FROM quiz q WHERE q.header = ?")) {
            statement.setString(1, header);
            resultSet = statement.executeQuery();
            resultSet.next();
            isExist = resultSet.getBoolean(1);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        } finally {
            closeResultSet(resultSet);
        }
        return isExist;
    }
}