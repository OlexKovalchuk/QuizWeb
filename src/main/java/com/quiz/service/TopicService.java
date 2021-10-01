package com.quiz.service;

import com.quiz.DB.DAOFactory;
import com.quiz.DB.DBConnection;
import com.quiz.DB.MySqlDAOFactory;
import com.quiz.DB.dao.impl.TopicDAO;
import com.quiz.entity.Topic;
import org.apache.log4j.Logger;

import java.util.List;

public class TopicService {
    private final DAOFactory factory;
    public static final Logger logger =Logger.getLogger(TopicService.class);


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
        try (DBConnection conn = factory.createConnection()) {
            TopicDAO topicDAO = factory.createTopicDAO(conn);
         return topicDAO.create(topic);
        }
    }

    public Topic getTopicById(int id) {
        try (DBConnection conn = factory.createConnection()) {
            TopicDAO topicDAO = factory.createTopicDAO(conn);
            return topicDAO.findById(id);
        }
    }

    public boolean deleteTopic(int id) {
        try (DBConnection conn = factory.createConnection()) {
            TopicDAO topicDAO = factory.createTopicDAO(conn);
          return   topicDAO.delete(id);
        }
    }

}