package com.ilya.dao;

import com.ilya.dao.connection.ConnectionProvider;
import com.ilya.dao.impl.TestDao;
import com.ilya.dao.impl.UserDao;
import com.ilya.entity.Test;
import com.ilya.entity.User;
import lombok.Getter;

@Getter
public class DaoFactory {
    @Getter
    private static DaoFactory instance;

    private static ConnectionProvider connectionProvider;

    private final Dao<User> userDao;
    private final Dao<Test> testDao;

    public static void init(ConnectionProvider connectionProvider) {
        DaoFactory.connectionProvider = connectionProvider;
        instance = new DaoFactory();
    }

    private DaoFactory() {
        userDao = new UserDao(connectionProvider);
        testDao = new TestDao(connectionProvider);
    }
}
