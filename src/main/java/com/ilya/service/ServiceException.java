package com.ilya.service;

public class ServiceException extends Exception {
    public ServiceException(Exception associatedException) {
        super(associatedException);
    }
}
