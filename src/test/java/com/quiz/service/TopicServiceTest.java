package com.quiz.service;

import com.quiz.DB.DAOFactory;
import com.quiz.DB.MySqlDAOFactory;
import com.quiz.entity.Topic;
import com.quiz.exceptions.UnsuccessfulQueryException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class TopicServiceTest {

    TopicService topicService;

    @Before
    public void setUp() throws Exception {
        topicService = new TopicService();
    }
    @Test
    public void deleteTopicWithWrongIdAndGetZeroChanges() {
        assertFalse(topicService.deleteTopic(2113));
    }
    @Test(expected= UnsuccessfulQueryException.class)
    public void createTopicWithUsedNameAndGetZeroChanges() {
        Topic topic = new Topic();
        topic.setName("Math");
        assertFalse(topicService.insertTopic(topic));
    }
    @Test
    public void getCorrectTopicById() {
        String actual =  topicService.getTopicById(3).getName();
        String expected =  "English";
        assertEquals(expected, actual);
    }
}