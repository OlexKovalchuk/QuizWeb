package com.quiz.DB.dao.interfaces;


import java.util.List;


public interface IAbstractDAO<E> {
    E findById(int id);
    List<E> findAll();
    boolean create(E e);
    boolean update(E e);
    boolean delete(int id);
}