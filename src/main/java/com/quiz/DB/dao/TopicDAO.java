package com.quiz.DB.dao;

import com.quiz.DB.LogConfigurator;
import com.quiz.entity.Topic;
import com.quiz.exceptions.UnsuccessfulQueryException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.quiz.DB.SqlConnection.closeResultSet;

public class TopicDAO {
    private final static Logger logger;
    private Connection connection;

    public TopicDAO(Connection connection) {
        this.connection = connection;

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

    public  void deleteTopic(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM topic where id=?;")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
    }
}