package com.quiz.DB.dao.impl;

import com.quiz.DB.LogConfigurator;
import com.quiz.DB.dao.interfaces.ITopicDAO;
import com.quiz.entity.Topic;
import com.quiz.exceptions.UnsuccessfulQueryException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.quiz.DB.DBConnection.closeResultSet;

public class TopicDAO extends AbstractDAO<Topic> implements ITopicDAO {
    private final static Logger logger;

    public TopicDAO(Connection connection) {
        super(connection);
    }

    @Override
    protected PreparedStatement getSelectEntityByIdStatement() throws SQLException {
        return connection.prepareStatement("Select * from topic where id =?");
    }

    @Override
    protected PreparedStatement getSelectAllEntitiesStatement() throws SQLException {
        return connection.prepareStatement("Select * from topic ");
    }

    @Override
    protected PreparedStatement getInsertEntityStatement() throws SQLException {
        return connection.prepareStatement("Insert Into  topic (name) values(?)");
    }

    @Override
    protected PreparedStatement getLastInsertIdStatement() throws SQLException {
        return connection.prepareStatement("Select LAST_INSERT_ID() from topic ");
    }

    @Override
    protected PreparedStatement getUpdateStatement() throws SQLException {
        return connection.prepareStatement("Update topic set name=? where id =?");
    }

    @Override
    protected PreparedStatement getDeleteStatement() throws SQLException {
        return connection.prepareStatement("delete from topic where id=?");
    }

    @Override
    protected int getEntityId(Topic entity) {
        return entity.getId();
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Topic entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setInt(2, entity.getId());
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Topic entity) throws SQLException {
        statement.setString(1, entity.getName());
    }

    @Override
    protected Topic getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Topic topic = new Topic();
        topic.setId(resultSet.getInt("id"));
        topic.setName(resultSet.getString("name"));
        return topic;
    }

    static {
        logger = LogConfigurator.getLogger(TopicDAO.class);
    }

    public List<Topic> getAllTopics() {
        List<Topic> topics = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM topic")) {
            resultSet = statement.executeQuery();
            Topic topic;
            while (resultSet.next()) {
                topic = new Topic();
                topic.setName(resultSet.getString("name"));
                topic.setId(resultSet.getInt("id"));
                topics.add(topic);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        } finally {
            closeResultSet(resultSet);
        }
        return topics;
    }

    public void insertTopic(String name) {
        try (PreparedStatement statement = connection.prepareStatement("insert into topic(name) values(?);")) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }

    }

    public String getTopicName(int id) {
        ResultSet resultSet = null;
        String name = "none";
        try (PreparedStatement statement = connection.prepareStatement("SELECT name FROM topic where id=?")) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();
            name = resultSet.getString("name");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        } finally {
            closeResultSet(resultSet);
        }
        return name;
    }

    public void deleteTopic(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM topic where id=?;")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
    }
}