package com.quiz.service;

import com.quiz.DB.DAOFactory;
import com.quiz.DB.LogConfigurator;
import com.quiz.DB.MySqlDAOFactory;
import com.quiz.DB.SqlConnection;
import com.quiz.DB.dao.TopicDAO;
import com.quiz.entity.Topic;
import org.apache.log4j.Logger;

import java.util.List;

public class TopicService {
    private DAOFactory factory;
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(TopicService.class);
    }

    public TopicService() {
        this.factory = new MySqlDAOFactory();
    }


    public List<Topic> getAllTopics() {
        try (SqlConnection conn = factory.createConnection()) {
            TopicDAO topicDAO = factory.createTopicDAO(conn);
            return topicDAO.getAllTopics();
        }
    }

    public void insertTopic(String name) {
        try (SqlConnection conn = factory.createConnection()) {
            TopicDAO topicDAO = factory.createTopicDAO(conn);
            topicDAO.insertTopic(name);
        }
    }

    public String getTopicById(int id) {
        try (SqlConnection conn = factory.createConnection()) {
            TopicDAO topicDAO = factory.createTopicDAO(conn);
            return topicDAO.getTopicName(id);
        }
    }

    public void deleteTopic(int id) {
        try (SqlConnection conn = factory.createConnection()) {
            TopicDAO topicDAO = factory.createTopicDAO(conn);
            topicDAO.deleteTopic(id);
        }
    }

}
