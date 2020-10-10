package com.ilya.dao;

import com.ilya.entity.Entity;

public interface Dao<T extends Entity> {
    T getById(int id);
    void add(T entity);
    void delete(T entity);
    void update(T entity);
}
