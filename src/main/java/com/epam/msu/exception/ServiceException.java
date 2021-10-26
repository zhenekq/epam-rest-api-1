package com.epam.msu.exception;

public class ServiceException extends RuntimeException{

    public ServiceException(){}

    public ServiceException(String message, Throwable throwable){
        super(message, throwable);
    }

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(Throwable throwable){
        super(throwable);
    }

}
