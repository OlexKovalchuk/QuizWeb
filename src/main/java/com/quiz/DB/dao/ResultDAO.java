package com.quiz.DB.dao;

import com.quiz.DB.LogConfigurator;
import com.quiz.entity.Result;
import com.quiz.exceptions.UnsuccessfulQueryException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.quiz.DB.SqlConnection.closeResultSet;

public class ResultDAO {
    private final static Logger logger;
    private Connection connection;

    public ResultDAO(Connection connection) {
        this.connection = connection;

    }

    static {
        logger = LogConfigurator.getLogger(ResultDAO.class);
    }

    public Result getResultByUserId(int id) {
        ResultSet resultSet = null;
        Result result = null;
        try (PreparedStatement statement = connection.prepareStatement("Select result.score,t.name as name,q.header " +
                "as header,result.start_date,result.complete_date " +
                "from result join quiz q on result.quiz_id = q.id  " +
                "join topic t on q.topic_id=t.id where result.user_id =? and result.id = " +
                "(Select last_insert_id());")) {
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            resultSet.next();
            result.setStartDate(resultSet.getTimestamp("start_date"));
            result.setCompleteDate(resultSet.getTimestamp("complete_date"));
            result.setScore(resultSet.getInt("score"));
            result.setTopicName(resultSet.getString("name"));
            result.setTestHeader(resultSet.getString("header"));
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        } finally {
            closeResultSet(resultSet);
        }
        return result;

    }

    public int getUserResultsCount(int id) {
        ResultSet resultSet = null;
        int count = 0;
        try (PreparedStatement statement = connection.prepareStatement("Select COUNT(*) as count FROM result where " +
                "result.user_id=?;")) {
            statement.setInt(1, id);
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

    public void updateResult(int score, int userId, int testId, int duration) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE result SET score=?, " +
                "complete_date=NOW" +
                "() " +
                "where user_id=? AND quiz_id=? AND start_date>?")) {
            statement.setInt(1, score);
            statement.setInt(2, userId);
            statement.setInt(3, testId);
            statement.setDate(4, new Date(System.currentTimeMillis() - 1000L * 60 * duration));
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }


    }

    public void insertResult(Result result) {
        try (
                PreparedStatement statement = connection.prepareStatement("insert into result(user_id,quiz_id," +
                        "start_date,score,complete_date) values (?,?,NOW(),?,NOW())")) {
            statement.setInt(1, result.getUserId());
            statement.setInt(2, result.getTestId());
            statement.setInt(3, result.getScore());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
    }

    public List<Result> getUserResults(int id, int offset, String type, String param) {
        List<Result> results = new ArrayList<>();
        String sql = "Select result.score,t.name as name,q.header as header,result.start_date,result.complete_date " +
                "from result join quiz q on result.quiz_id = q.id  " +
                "join topic t on q.topic_id=t.id " +
                " where user_id=?  " + type + " " + param + " limit 5 " +
                "offset ?";
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, id);
            statement.setInt(2, offset);
            resultSet = statement.executeQuery();
            Result result;
            while (resultSet.next()) {
                result = new Result();
                result.setStartDate(resultSet.getTimestamp("start_date"));
                result.setCompleteDate(resultSet.getTimestamp("complete_date"));
                result.setScore(resultSet.getInt("score"));
                result.setTopicName(resultSet.getString("name"));
                result.setTestHeader(resultSet.getString("header"));
                results.add(result);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        } finally {
            closeResultSet(resultSet);
        }
        return results;
    }
}
