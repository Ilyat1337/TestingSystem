package com.ilya.service;

import com.ilya.service.impl.TestServiceImpl;
import com.ilya.service.interfaces.TestService;
import lombok.Getter;

@Getter
public class ServiceFactory {
    @Getter
    private static final ServiceFactory instance = new ServiceFactory();

    private final TestService testService = new TestServiceImpl();
}
