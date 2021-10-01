package com.quiz.DB.dao.interfaces;

import com.quiz.entity.Quiz;
import com.quiz.web.utils.Pageable;

import java.util.List;

public interface IQuizDAO extends IAbstractDAO<Quiz> {
     int getQuizCount(int topicId);
     List<Quiz> getAllQuizzes(int topicId, Pageable pageable);
     boolean insertQuiz(Quiz quiz);
     boolean isQuizHasResults(int id);
     boolean archiveQuiz(int id);
     boolean isHeaderExists(String header);
}