package com.quiz.DB.dao;

import com.quiz.DB.DBConnectionPool;
import com.quiz.DB.dao.impl.ResultDAO;
import com.quiz.entity.Result;
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
public class ResultDAOTest {
    @Mock
    private Connection connection;
    private ResultDAO resultDAO;

    @Before
    public void setUp() throws Exception {connection = DBConnectionPool.getConnection();
        resultDAO = new ResultDAO(connection);
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void createResultWithWrongUserIdAndGetExceptions() throws UnsuccessfulQueryException {
        Result result = new Result.Builder()
                .setTestId(6)
                .setScore(12)
                .setUserId(2141242)
                .build();
        resultDAO.create(result);
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void createResultWithWrongQuizIdAndGetExceptions() throws UnsuccessfulQueryException {
        Result result = new Result.Builder()
                .setTestId(6142)
                .setScore(12)
                .setUserId(2)
                .build();
        resultDAO.create(result);
    }

    @Test
    public void deleteResultWithWrongIdAndGetZeroChanges() throws UnsuccessfulQueryException {
        assertFalse(resultDAO.delete(1234));
    }

    @Test
    public void updateQuizWithWrongIdAndGetZeroChanges() throws UnsuccessfulQueryException {
        Result result = new Result.Builder()
                .setId(2134)
                .setTestId(6)
                .setScore(12)
                .setUserId(2)
                .build();
        assertFalse(resultDAO.update(result));
    }
    @After
    public void tearDown() throws Exception {
        connection.close();
    }


}