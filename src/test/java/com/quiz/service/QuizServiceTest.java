package com.quiz.service;

import com.quiz.entity.Quiz;
import com.quiz.exceptions.UnsuccessfulQueryException;
import com.quiz.web.utils.Pageable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class QuizServiceTest {

 QuizService quizService;

    @Before
    public void setUp() throws Exception {
        quizService = new QuizService();
    }
    @Test(expected = UnsuccessfulQueryException.class)
    public void deleteQuizWithWrongIdAndGetZeroChanges() {
        assertFalse(quizService.deleteQuiz(2113));
    }
    @Test
    public void getCorrectQuizById() {
       String actual =  quizService.getQuiz(6).getHeader();
       String expected =  "Algebra";
        assertEquals(expected, actual);
    }
    @Test
    public void getAllResultsWithPagination() throws Exception {
        Pageable pageable = new Pageable.Builder()
                .page(1)
                .size(5)
                .sort("count")
                .ASC()
                .build();
        List<Quiz> quizzes =  quizService.getAllQuizzes(1,pageable);
        int expectedSize = 5;
        int actual =quizzes.size();
        assertTrue(actual<=expectedSize);
    }

}