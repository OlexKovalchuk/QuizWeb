package com.quiz.DB.dao.impl;

import com.quiz.DB.LogConfigurator;
import com.quiz.DB.dao.interfaces.IAbstractDAO;
import com.quiz.exceptions.UnsuccessfulQueryException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.quiz.DB.DBConnection.closeResultSet;

public abstract class AbstractDAO<E> implements IAbstractDAO<E> {
    private final static Logger logger;
    protected Connection connection;

    protected AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    static {
        logger = LogConfigurator.getLogger(QuizDAO.class);
    }

    protected abstract PreparedStatement getSelectEntityByIdStatement() throws SQLException;

    protected abstract PreparedStatement getSelectAllEntitiesStatement() throws SQLException;

    protected abstract PreparedStatement getInsertEntityStatement() throws SQLException;

    protected abstract PreparedStatement getLastInsertIdStatement() throws SQLException;

    protected abstract PreparedStatement getUpdateStatement() throws SQLException;

    protected abstract PreparedStatement getDeleteStatement() throws SQLException;

    protected abstract int getEntityId(E entity);

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, E entity) throws SQLException;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, E entity) throws SQLException;

    protected abstract E getEntityFromResultSet(ResultSet resultSet) throws SQLException;


    @Override
    public E findById(int id) {
        E result=null;
        ResultSet resultSet = null;
        try (PreparedStatement statement = getSelectEntityByIdStatement()) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = getEntityFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        } finally {
            closeResultSet(resultSet);
        }
        return result;
    }

    @Override
    public List<E> findAll() {
        List<E> entities = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement statement = getSelectAllEntitiesStatement()) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                E entity = getEntityFromResultSet(resultSet);
                entities.add(entity);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        } finally {
            closeResultSet(resultSet);
        }
        return entities;
    }

    @Override
    public boolean create(E entity) {
        int changes =0;
        Objects.requireNonNull(entity);
        try (PreparedStatement statement = getInsertEntityStatement()) {
            prepareStatementForInsert(statement, entity);
               changes = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        return changes>0;
    }

    private int getLastInsertId(E entity) throws SQLException {
        ResultSet resultSet =null;
        int lastInsertId=0;
        try (PreparedStatement statement = getLastInsertIdStatement()) {
             resultSet = statement.executeQuery();
            if (resultSet.next()) {
                lastInsertId= resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }finally {
            closeResultSet(resultSet);
        }
        return lastInsertId;
    }

    @Override
    public boolean update(E entity) {
        int changes =0;
        Objects.requireNonNull(entity);
        int lastInsertId = getEntityId(entity);
        try (PreparedStatement statement = getUpdateStatement()) {
            prepareStatementForUpdate(statement, entity);
            changes =statement.executeUpdate();
        }catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        return changes>0;
    }

    @Override
    public boolean delete(int id) {
        int changes =0;
        try (PreparedStatement statement = getDeleteStatement()) {
            statement.setInt(1, id);
          changes=  statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        return changes>0;
    }

}