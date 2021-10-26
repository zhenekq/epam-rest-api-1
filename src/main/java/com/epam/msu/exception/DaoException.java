package com.epam.msu.exception;

public class DaoException extends RuntimeException{

    public DaoException(){}

    public DaoException(String message, Throwable throwable){
        super(message, throwable);
    }

    public DaoException(String message){
        super(message);
    }

    public DaoException(Throwable throwable){
        super(throwable);
    }

}
