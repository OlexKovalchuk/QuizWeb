package com.quiz.DB.dao;

import com.quiz.exceptions.UnsuccessfulQueryException;

import java.util.List;

/**
 * Basic DAO class which is extended by concrete DAO classes
 * @param <K> represents key of the database table
 * @param <T> represents entity type
 */
public interface AbstractDAO<K, T > {
    List<T> findAll() throws UnsuccessfulQueryException;
    T findById(K id) throws UnsuccessfulQueryException;
    boolean delete(K id) throws UnsuccessfulQueryException;
    boolean create(T entity) throws UnsuccessfulQueryException;
}
