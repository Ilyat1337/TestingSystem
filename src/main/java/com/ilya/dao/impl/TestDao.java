package com.ilya.dao.impl;

import com.ilya.dao.Creteria;
import com.ilya.dao.Dao;
import com.ilya.dao.connection.ConnectionProvider;
import com.ilya.entity.Test;

import java.util.List;

public class TestDao implements Dao<Test> {
    private final ConnectionProvider connectionProvider;

    public TestDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public Test getById(int id) {
        return null;
    }

    @Override
    public List<Test> getAll() {
        return null;
    }

    @Override
    public List<Test> getByCriteria(Creteria creteria) {
        return null;
    }

    @Override
    public void add(Test entity) {

    }

    @Override
    public void delete(Test entity) {

    }

    @Override
    public void update(Test entity) {

    }
}
