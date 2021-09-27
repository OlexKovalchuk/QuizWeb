package com.quiz.DB.dao;

import com.quiz.DB.DBConnectionPool;
import com.quiz.DB.dao.impl.AnswerDAO;
import com.quiz.entity.Answer;
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
public class AnswerDAOTest {
    @Mock
    private Connection connection;
    private AnswerDAO answerDAO;

    @Before
    public void setUp() throws Exception {
        connection = DBConnectionPool.getConnection();
        answerDAO = new AnswerDAO(connection);
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void createAnswerWithWrongQuestionIdAndGetExceptions() throws UnsuccessfulQueryException {
        Answer answer = new Answer();
        answer.setQuestionId(123467);
        answer.setAnswer(1);
        answer.setDescription("UnusedAnswer");
        answerDAO.create(answer);
    }

    @Test
    public void deleteAnswerWithWrongIdAndGetZeroChanges() throws UnsuccessfulQueryException {
        assertFalse(answerDAO.delete(1234));
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void updateAnswerWithWrongIdAndGetException() throws UnsuccessfulQueryException {
        Answer answer = new Answer();
        answer.setQuestionId(123467);
        answer.setAnswer(1);
        answer.setDescription("UnusedAnswer");
        answerDAO.update(answer);
    }
    @After
    public void tearDown() throws Exception {
        connection.close();
    }

}