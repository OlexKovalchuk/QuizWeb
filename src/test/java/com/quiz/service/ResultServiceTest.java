package com.quiz.service;

import com.quiz.entity.Result;
import com.quiz.web.utils.Pageable;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ResultServiceTest {

    ResultService resultService;

    @Before
    public void setUp() throws Exception {
        resultService = new ResultService();
    }
    @Test
    public void getAllUserResultsWithPagination() throws Exception {
      Pageable pageable = new Pageable.Builder()
                .page(1)
                .size(5)
                .sort("score")
                .ASC()
                .build();
        List<Result> results =  resultService.getUserResultsById(1,pageable);
        int expectedSize = 5;
        int actual =results.size();
        assertTrue(actual<=expectedSize);
    }
}