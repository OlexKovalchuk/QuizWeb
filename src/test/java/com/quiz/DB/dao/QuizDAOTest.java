package com.quiz.DB.dao;

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
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class QuizDAOTest {
    @Mock
    private Connection connection;
    private QuizDAO quizDAO;

    @Before
    public void setUp() throws Exception {
       connection = mock(Connection.class);
        quizDAO = new QuizDAO(connection);
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void createQuizWithWrongTopicIdAndGetExceptions() throws UnsuccessfulQueryException {
        Quiz quiz = new Quiz.Builder()
                .setTopicId(21421)
                .setDescription("description")
                .setHeader("head")
                .setDifficult("easy")
                .setDuration(13)
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
                .setId(12134)
                .setTopicId(1)
                .setDescription("description")
                .setHeader("head")
                .setDifficult("easy")
                .setDuration(13)
                .build();
        assertFalse(quizDAO.update(quiz));
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void createQuizWithWrongDifficultAndGetException() throws UnsuccessfulQueryException {
        Quiz quiz = new Quiz.Builder()
                .setTopicId(1)
                .setDescription("description")
                .setHeader("head")
                .setDifficult("adsdas")
                .setDuration(13)
                .build();
        quizDAO.create(quiz);
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void createQuizWithUsedHeaderAndGetException() throws UnsuccessfulQueryException {
        Quiz quiz = new Quiz.Builder()
                .setTopicId(1)
                .setDescription("description")
                .setHeader("Java")
                .setDifficult("easy")
                .setDuration(13)
                .build();
        quizDAO.create(quiz);
    }
//
//    @Test
//    public void getTrueIfQuizCreated() throws UnsuccessfulQueryException {
//        List<Question> questions = new ArrayList<>();
//        Question question = new Question();
//        question.setDescription("newQuestion");
//        List<Answer> answers = new ArrayList<>();
//        Answer answer = new Answer();
//        answer.setAnswer(1);
//        answer.setDescription("newAnswer");
//        answers.add(answer);
//        question.setAnswers(answers);
//        questions.add(question);
//        Quiz quiz = new Quiz.Builder()
//                .setTopicId(1)
//                .setDescription("description")
//                .setHeader("quizz")
//                .setDifficult("easy")
//                .setDuration(13)
//                .setQuestions(questions)
//                .build();
//        Assert.assertTrue(quizDAO.insertQuiz(quiz));
//    }


    @Test(expected = UnsuccessfulQueryException.class)
    public void updateQuizWithUsedHeaderAndGetException() throws UnsuccessfulQueryException {
        Quiz quiz = new Quiz.Builder()
                .setId(51)
                .setTopicId(1)
                .setDescription("description")
                .setHeader("Java")
                .setDifficult("easy")
                .setDuration(13)
                .build();
        quizDAO.create(quiz);

    }

    @After
    public void tearDown() throws Exception {
        connection.close();
    }

}