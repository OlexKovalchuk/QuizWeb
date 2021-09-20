package com.quiz.DB.dao;

import com.quiz.DB.LogConfigurator;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class RoleDAO {
    private final static Logger logger;
    private Connection connection;
    public RoleDAO(Connection connection) {
        this.connection = connection;

    }
    static {
        logger = LogConfigurator.getLogger(QuizDAO.class);
    }


}
