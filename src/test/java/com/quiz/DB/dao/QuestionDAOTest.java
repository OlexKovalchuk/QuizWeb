package com.quiz.DB.dao;

import com.quiz.DB.DBConnectionPool;
import com.quiz.DB.dao.impl.QuestionDAO;
import com.quiz.entity.Question;
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
public class QuestionDAOTest {
    @Mock
    private Connection connection;
    private QuestionDAO questionDAO;

    @Before
    public void setUp() throws Exception {connection = DBConnectionPool.getConnection();
        questionDAO = new QuestionDAO(connection);
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void createQuestionWithWrongQuizIdAndGetExceptions() throws  UnsuccessfulQueryException {
        Question question = new Question();
        question.setTestId(123467);
        question.setDescription("UnusedAnswer");
        questionDAO.create(question);
    }

    @Test
    public void deleteAnswerWithWrongIdAndGetZeroChanges() throws  UnsuccessfulQueryException {
        assertFalse(questionDAO.delete(1234));
    }

    @Test
    public void updateAnswerWithWrongIdAndGetZeroChanges() throws UnsuccessfulQueryException {
        Question question = new Question();
        question.setId(123467);
        question.setTestId(123467);
        question.setDescription("UnusedAnswer");
       assertFalse( questionDAO.update(question));
    }
    @After
    public void tearDown() throws Exception {
        connection =null;
        questionDAO=null;
    }
}