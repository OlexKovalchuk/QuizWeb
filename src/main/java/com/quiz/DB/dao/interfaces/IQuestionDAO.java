package com.quiz.DB.dao.interfaces;

import com.quiz.entity.Question;
import com.quiz.exceptions.UnsuccessfulQueryException;

import java.util.List;

public interface IQuestionDAO  extends IAbstractDAO<Question> {
     List<Question> getQuestionsByQuizId(int quizID) throws UnsuccessfulQueryException ;
     boolean insertQuestions(List<Question> questions, int quizId) ;
    }