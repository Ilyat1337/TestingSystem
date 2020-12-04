package com.ilya.dao;

import com.ilya.entity.Entity;

import java.util.List;

public interface Dao<T extends Entity> {
    T getById(int id) throws DaoException;
    List<T> getAll() throws DaoException;
    List<T> getByCriteria(Creteria creteria) throws DaoException;
    void add(T entity) throws DaoException;
    void delete(T entity) throws DaoException;
    void update(T entity) throws DaoException;
}
