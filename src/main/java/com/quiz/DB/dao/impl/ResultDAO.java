package com.quiz.DB.dao.impl;

import com.quiz.DB.LogConfigurator;
import com.quiz.DB.dao.interfaces.IResultDAO;
import com.quiz.entity.Result;
import com.quiz.exceptions.UnsuccessfulQueryException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.quiz.DB.DBConnection.closeResultSet;

public class ResultDAO extends AbstractDAO<Result> implements IResultDAO {
    private final static Logger logger;

    public ResultDAO(Connection connection) {
        super(connection);
    }

    @Override
    protected PreparedStatement getSelectEntityByIdStatement() throws SQLException {
        return connection.prepareStatement("Select result.score,t.name as name,q.header as header,result.start_date," +
                "result.complete_date " +
                "from result join quiz q on result.quiz_id = q.id  " +
                "join topic t on q.topic_id=t.id where result.id=?");
    }

    @Override
    protected PreparedStatement getSelectAllEntitiesStatement() throws SQLException {
        return connection.prepareStatement("Select result.id, result.score,t.name as name,q.header as header,result" +
                ".start_date,result.complete_date " +
                "from result join quiz q on result.quiz_id = q.id  " +
                "join topic t on q.topic_id=t.id");

    }

    @Override
    protected PreparedStatement getInsertEntityStatement() throws SQLException {
        return connection.prepareStatement("Insert Into result (score, user_id,quiz_id, complete_date, start_date " +
                ") values (?,?,?,NOW(),NOW())");
    }

    @Override
    protected PreparedStatement getLastInsertIdStatement() throws SQLException {
        return connection.prepareStatement("Select  last_insert_id() from result");
    }

    @Override
    protected PreparedStatement getUpdateStatement() throws SQLException {
        return connection.prepareStatement("Update  result  set score=?,complete_date=NOW() where result.id=(Select  " +
                "last_insert_id()  where user_id=? and quiz_id=? )");
    }

    @Override
    protected PreparedStatement getDeleteStatement() throws SQLException {
        return connection.prepareStatement("Delete  from result where result.id=?");

    }

    @Override
    protected int getEntityId(Result entity) {
        return entity.getId();
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Result entity) throws SQLException {
        statement.setInt(1, entity.getScore());
        statement.setInt(2, entity.getUserId());
        statement.setInt(3, entity.getTestId());
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Result entity) throws SQLException {
        statement.setInt(1, entity.getScore());
        statement.setInt(2, entity.getUserId());
        statement.setInt(3, entity.getTestId());
    }

    @Override
    protected Result getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new Result.Builder()
                .setId(resultSet.getInt("id"))
                .setTopicName(resultSet.getString("name"))
                .setTestHeader(resultSet.getString("header"))
                .setScore(resultSet.getInt("score"))
                .setCompleteDate(resultSet.getTimestamp("complete_date"))
                .setStartDate(resultSet.getTimestamp("start_date"))
                .build();

    }

    static {
        logger = LogConfigurator.getLogger(ResultDAO.class);
    }

    public Result getResultByUserId(int id) {
        ResultSet resultSet = null;
        Result result = new Result();
        try (PreparedStatement statement = connection.prepareStatement("Select result.score,t.name as name,q.header " +
                "as header,result.start_date,result.complete_date " +
                "from result join quiz q on result.quiz_id = q.id  " +
                "join topic t on q.topic_id=t.id where result.user_id =? and result.id = " +
                "(Select last_insert_id());")) {
            statement.setInt(1, id);
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

    public void updateResult(int score, int userId, int testId) {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement("UPDATE result SET score=?, " +
                "complete_date=NOW" +
                "() " +
                "where result.id=(Select  last_insert_id()  where user_id=? and quiz_id=? ) ")) {
            statement.setInt(1, score);
            statement.setInt(2, userId);
            statement.setInt(3, testId);
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