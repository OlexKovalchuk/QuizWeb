package com.quiz.DB.dao.interfaces;

import com.quiz.entity.Answer;
import com.quiz.exceptions.UnsuccessfulQueryException;

import java.util.List;

public interface IAnswerDAO extends IAbstractDAO<Answer> {
    List<Answer> getAnswersByQuestionId(int questionId) throws UnsuccessfulQueryException;

    boolean insertAnswers(List<Answer> answers, int questionId);

}