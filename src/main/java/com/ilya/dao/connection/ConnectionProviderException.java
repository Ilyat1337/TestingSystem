package com.ilya.dao.connection;

public class ConnectionProviderException extends Exception{
    public ConnectionProviderException(Exception associatedException) {
        super(associatedException);
    }
}
