package com.quiz.service;

import com.quiz.DB.DAOFactory;
import com.quiz.DB.DBConnection;
import com.quiz.DB.MySqlDAOFactory;
import com.quiz.DB.dao.impl.TopicDAO;
import com.quiz.entity.Topic;
import com.quiz.exceptions.UnsuccessfulQueryException;
import org.apache.log4j.Logger;

import java.util.List;

public class TopicService {
    private final DAOFactory factory;
    public static final Logger logger = Logger.getLogger(TopicService.class);


    public TopicService() {
        this.factory = new MySqlDAOFactory();
    }


    public List<Topic> getAllTopics() {
        try (DBConnection conn = factory.createConnection()) {
            TopicDAO topicDAO = factory.createTopicDAO(conn);
            return topicDAO.findAll();
        }
    }

    public boolean insertTopic(Topic topic) {
        try (DBConnection connection = factory.createConnection()) {
            TopicDAO topicDAO = factory.createTopicDAO(connection);
            try {
                connection.setAutoCommit(false);
                topicDAO.create(topic);
                connection.commit();
            } catch (UnsuccessfulQueryException e) {
                logger.error(e.getMessage());
                connection.rollback();
                return false;
            }
            return true;
        }
    }

    public Topic getTopicById(int id) {
        try (DBConnection conn = factory.createConnection()) {
            TopicDAO topicDAO = factory.createTopicDAO(conn);
            return topicDAO.findById(id);
        }
    }

    public boolean deleteTopic(int id) {
        try (DBConnection connection = factory.createConnection()) {
            TopicDAO topicDAO = factory.createTopicDAO(connection);
            try {
                connection.setAutoCommit(false);
                topicDAO.delete(id);
                connection.commit();
            } catch (UnsuccessfulQueryException e) {
                logger.error(e.getMessage());
                connection.rollback();
                return false;
            }
            return true;
        }
    }

}