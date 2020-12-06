package com.ilya.service.interfaces;

import com.ilya.dto.TestWithQuestions;
import com.ilya.service.ServiceException;

public interface TestService {
    void addTest(TestWithQuestions testWithQuestions) throws ServiceException;
}
