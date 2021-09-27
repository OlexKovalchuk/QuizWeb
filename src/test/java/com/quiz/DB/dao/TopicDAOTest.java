package com.quiz.DB.dao;

import com.quiz.DB.DBConnectionPool;
import com.quiz.DB.dao.impl.TopicDAO;
import com.quiz.entity.Topic;
import com.quiz.exceptions.UnsuccessfulQueryException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;

import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class TopicDAOTest {
    @Mock
    private Connection connection;
    private TopicDAO topicDAO;

    @Before
    public void setUp() throws Exception {connection = DBConnectionPool.getConnection();
        topicDAO = new TopicDAO(connection);
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void createTopicWithUsedNameAndGetExceptions() throws UnsuccessfulQueryException {
        Topic topic = new Topic();
        topic.setName("Math");
        topicDAO.create(topic);
    }
    @Test
    public void updateTopicWithWrongIdAndGetZeroChanges() throws UnsuccessfulQueryException {
        Topic topic = new Topic();
        topic.setId(12312);
        topic.setName("newTopicName");
        assertFalse(topicDAO.update(topic));
    }
    @Test
    public void deleteTopicWithWrongIdAndGetZeroChanges() throws UnsuccessfulQueryException {
        assertFalse(topicDAO.delete(1234));
    }
    @After
    public void tearDown() throws Exception {
        connection.close();
    }

}