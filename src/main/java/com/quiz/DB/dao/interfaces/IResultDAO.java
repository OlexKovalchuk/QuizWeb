package com.quiz.DB.dao.interfaces;

import com.quiz.entity.Result;
import com.quiz.web.utils.Pageable;

import java.util.List;

public interface IResultDAO extends IAbstractDAO<Result> {
    List<Result> getUserResults(int id, Pageable pageable);

    int getUserResultsCount(int id);

    Result getResultByUserId(int id);
}