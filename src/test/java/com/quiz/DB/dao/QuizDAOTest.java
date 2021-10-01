package com.quiz.DB.dao;

import com.quiz.DB.DBConnectionPool;
import com.quiz.DB.dao.impl.QuizDAO;
import com.quiz.entity.Quiz;
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
public class QuizDAOTest {
    @Mock
    private Connection connection;
    private QuizDAO quizDAO;

    @Before
    public void setUp() throws Exception {
        connection = DBConnectionPool.getConnection();
        quizDAO = new QuizDAO(connection);
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void createQuizWithWrongTopicIdAndGetExceptions() throws UnsuccessfulQueryException {
        Quiz quiz = new Quiz.Builder()
                .topicId(21421)
                .description("description")
                .header("head")
                .difficult("easy")
                .duration(13)
                .build();
        quizDAO.create(quiz);
    }

    @Test
    public void deleteQuizWithWrongIdAndGetZeroChanges() throws UnsuccessfulQueryException {
        assertFalse(quizDAO.delete(1234));
    }

    @Test
    public void updateQuizWithWrongIdAndGetZeroChanges() throws UnsuccessfulQueryException {
        Quiz quiz = new Quiz.Builder()
                .id(12134)
                .topicId(1)
                .description("description")
                .header("head")
                .difficult("easy")
                .duration(13)
                .build();
        assertFalse(quizDAO.update(quiz));
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void createQuizWithWrongDifficultAndGetException() throws UnsuccessfulQueryException {
        Quiz quiz = new Quiz.Builder()
                .topicId(1)
                .description("description")
                .header("head")
                .difficult("adsdas")
                .duration(13)
                .build();
        quizDAO.create(quiz);
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void createQuizWithUsedHeaderAndGetException() throws UnsuccessfulQueryException {
        Quiz quiz = new Quiz.Builder()
                .topicId(1)
                .description("description")
                .header("Java")
                .difficult("easy")
                .duration(13)
                .build();
        quizDAO.create(quiz);
    }



    @Test(expected = UnsuccessfulQueryException.class)
    public void updateQuizWithUsedHeaderAndGetException() throws UnsuccessfulQueryException {
        Quiz quiz = new Quiz.Builder()
                .id(51)
                .topicId(1)
                .description("description")
                .header("Java")
                .difficult("easy")
                .duration(13)
                .build();
        quizDAO.create(quiz);

    }

    @After
    public void tearDown() throws Exception {
        connection.close();
    }

}