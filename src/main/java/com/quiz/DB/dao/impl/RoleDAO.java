package com.quiz.DB.dao.impl;

import org.apache.log4j.Logger;

import java.sql.Connection;

public class RoleDAO {
    public static final Logger logger =Logger.getLogger(RoleDAO.class);

    private final Connection connection;
    public RoleDAO(Connection connection) {
        this.connection = connection;
    }


}