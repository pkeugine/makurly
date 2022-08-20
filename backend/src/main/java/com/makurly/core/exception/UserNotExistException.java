package com.makurly.core.exception;

public class UserAlreadyExistException extends RuntimeException{

    public UserAlreadyExistException() {
        super();
    }

    public UserAlreadyExistException(String message){
        super(message);
    }

    public UserAlreadyExistException(Throwable ex){
        super(ex);
    }

    public UserAlreadyExistException(String message, Throwable ex){
        super(message, ex);
    }
}
