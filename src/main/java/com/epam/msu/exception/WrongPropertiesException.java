package com.epam.msu.exception;

public class WrongPropertiesException extends RuntimeException{
    public WrongPropertiesException(String exception){
        super(exception);
    }

    public WrongPropertiesException(Throwable throwable){
        super(throwable);
    }

    public WrongPropertiesException(String exception, Throwable throwable){
        super(exception, throwable);
    }
}
