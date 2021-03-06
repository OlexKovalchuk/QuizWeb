package com.quiz.service;

import com.quiz.DB.DAOFactory;
import com.quiz.DB.DBConnection;
import com.quiz.DB.MySqlDAOFactory;
import com.quiz.DB.dao.impl.ResultDAO;
import com.quiz.entity.Result;
import com.quiz.exceptions.UnsuccessfulQueryException;
import com.quiz.web.utils.Pageable;
import org.apache.log4j.Logger;

import java.util.List;

public class ResultService {
    private final DAOFactory factory;
    public static final Logger logger = Logger.getLogger(ResultService.class);


    public ResultService() {
        this.factory = new MySqlDAOFactory();
    }

    public void updateResult(Result result) {
        try (DBConnection connection = factory.createConnection()) {
            ResultDAO resultDAO = factory.createResultDAO(connection);
            try {
                connection.setAutoCommit(false);
                resultDAO.update(result);
                connection.commit();
            } catch (UnsuccessfulQueryException e) {
                logger.error(e.getMessage());
                connection.rollback();
            }
        }

    }

    public boolean insertResult(Result result) {
        try (DBConnection connection = factory.createConnection()) {
            ResultDAO resultDAO = factory.createResultDAO(connection);
            try {
                connection.setAutoCommit(false);
                resultDAO.create(result);
                connection.commit();
            } catch (UnsuccessfulQueryException e) {
                logger.error(e.getMessage());
                connection.rollback();
                return false;
            }
            return true;
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


    public List<Result> getUserResultsById(int id, Pageable pageable) {
        try (DBConnection conn = factory.createConnection()) {
            ResultDAO resultDAO = factory.createResultDAO(conn);
            return resultDAO.getUserResults(id, pageable);
        }
    }
}