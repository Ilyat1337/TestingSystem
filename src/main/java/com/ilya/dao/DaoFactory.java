package com.ilya.dao;

import com.ilya.dao.connection.ConnectionProvider;
import com.ilya.dao.impl.SqlTestDao;
import com.ilya.dao.impl.SqlUserDao;
import com.ilya.dao.interfaces.TestDao;
import com.ilya.entity.User;
import lombok.Getter;

@Getter
public class DaoFactory {
    @Getter
    private static DaoFactory instance;

    private static ConnectionProvider connectionProvider;

    private final Dao<User> userDao;
    private final TestDao testDao;

    public static void init(ConnectionProvider connectionProvider) {
        DaoFactory.connectionProvider = connectionProvider;
        instance = new DaoFactory();
    }

    private DaoFactory() {
        userDao = new SqlUserDao(connectionProvider);
        testDao = new SqlTestDao(connectionProvider);
    }
}
