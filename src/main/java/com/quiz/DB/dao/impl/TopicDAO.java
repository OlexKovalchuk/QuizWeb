package com.quiz.DB.dao.impl;

import com.quiz.DB.dao.interfaces.ITopicDAO;
import com.quiz.entity.Topic;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TopicDAO extends AbstractDAO<Topic> implements ITopicDAO {
    public static final Logger logger =Logger.getLogger(TopicDAO.class);


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

}