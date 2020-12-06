package com.ilya.service.impl;

import com.ilya.dao.DaoException;
import com.ilya.dao.DaoFactory;
import com.ilya.dto.TestWithQuestions;
import com.ilya.service.ServiceException;
import com.ilya.service.interfaces.TestService;

public class TestServiceImpl implements TestService {
    @Override
    public void addTest(TestWithQuestions testWithQuestions) throws ServiceException {
        try {
            DaoFactory.getInstance().getTestDao().addTest(testWithQuestions);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
