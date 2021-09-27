package com.quiz.service;

import com.quiz.DB.*;
import com.quiz.DB.dao.impl.ResultDAO;
import com.quiz.entity.Result;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ResultService {
    private final DAOFactory factory;
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(ResultService.class);
    }

    public ResultService() {
        this.factory = new MySqlDAOFactory();
    }

    public void updateResult(Result result) {
        try (DBConnection conn = factory.createConnection()) {
            ResultDAO resultDAO = factory.createResultDAO(conn);
            resultDAO.update(result);
        }

    }

    public boolean insertResult(Result result) {
        try (DBConnection conn = factory.createConnection()) {
            ResultDAO resultDAO = factory.createResultDAO(conn);
         return   resultDAO.create(result);
        }
    }

    public List<Result> getUserResultsWithPagination(int id, int page) {
        try (DBConnection conn = factory.createConnection()) {
            ResultDAO resultDAO = factory.createResultDAO(conn);
            return resultDAO.getUserResults(id, (page - 1) * 5, "", "");
        }
    }

    public int getUserResultsCount(int id) {
        try (DBConnection conn = factory.createConnection()) {
            ResultDAO resultDAO = factory.createResultDAO(conn);
            return resultDAO.getUserResultsCount(id);
        }
    }
    public Result getUserResultById(int id) {
        try (DBConnection conn = factory.createConnection()) {
            ResultDAO resultDAO = factory.createResultDAO(conn);
            return resultDAO.getResultByUserId(id);
        }
    }
    @Sort(type = "order by", param = "result.score")
    public List<Result> getUserResultsWithPaginationByScore(int id, int page) {
        try (DBConnection conn = factory.createConnection()) {
            ResultDAO resultDAO = factory.createResultDAO(conn);
            try {
                Sort sortAnnotation =
                        (Sort) ResultService.class.getMethod("getUserResultsWithPaginationByScore", int.class,
                                int.class).getAnnotation(Sort.class);
                return resultDAO.getUserResults(id, (page - 1) * 5, sortAnnotation.type(), sortAnnotation.param());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    @Sort(type = "order by", param = "result.start_date")
    public List<Result> getUserResultsWithPaginationByDate(int id, int page) {
        try (DBConnection conn = factory.createConnection()) {
            ResultDAO resultDAO = factory.createResultDAO(conn);
            try {
                Sort sortAnnotation =
                        (Sort) ResultService.class.getMethod("getUserResultsWithPaginationByDate", int.class,
                                int.class).getAnnotation(Sort.class);
                return resultDAO.getUserResults(id, (page - 1) * 5, sortAnnotation.type(), sortAnnotation.param());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    @Sort(type = "order by", param = "t.name")
    public List<Result> getUserResultsWithPaginationByTopic(int id, int page) {
        try (DBConnection conn = factory.createConnection()) {
            ResultDAO resultDAO = factory.createResultDAO(conn);
            try {
                Sort sortAnnotation =
                        (Sort) ResultService.class.getMethod("getUserResultsWithPaginationByTopic", int.class,
                                int.class).getAnnotation(Sort.class);
                return resultDAO.getUserResults(id, (page - 1) * 5, sortAnnotation.type(), sortAnnotation.param());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    @Sort(type = "order by", param = "q.header")
    public List<Result> getUserResultsWithPaginationByQuiz(int id, int page) {
        try (DBConnection conn = factory.createConnection()) {
            ResultDAO resultDAO = factory.createResultDAO(conn);
            try {
                Sort sortAnnotation =
                        (Sort) ResultService.class.getMethod("getUserResultsWithPaginationByQuiz", int.class,
                                int.class).getAnnotation(Sort.class);
                return resultDAO.getUserResults(id, (page - 1) * 5, sortAnnotation.type(), sortAnnotation.param());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }
}