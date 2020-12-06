package com.ilya.dao.interfaces;

import com.ilya.dao.DaoException;
import com.ilya.dto.TestWithQuestions;

public interface TestDao {
    void addTest(TestWithQuestions testWithQuestions) throws DaoException;
}
