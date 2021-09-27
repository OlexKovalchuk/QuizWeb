package com.quiz.service;

import com.quiz.DB.DAOFactory;
import com.quiz.DB.DBConnection;
import com.quiz.DB.MySqlDAOFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class QuizServiceTest {

 QuizService quizService;

    @Before
    public void setUp() throws Exception {
        quizService = new QuizService();
    }
    @Test
    public void deleteQuizWithWrongIdAndGetZeroChanges() {
        assertFalse(quizService.deleteQuiz(2113));
    }
    @Test
    public void getCorrectQuizById() {
       String actual =  quizService.getQuiz(6).getHeader();
       String expected =  "Algebra";
        assertEquals(expected, actual);
    }

}