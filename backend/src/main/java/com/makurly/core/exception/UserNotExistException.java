package com.makurly.core.exception;

public class UserNotExistException extends RuntimeException{

    public UserNotExistException() {
        super();
    }

    public UserNotExistException(String message){
        super(message);
    }

    public UserNotExistException(Throwable ex){
        super(ex);
    }

    public UserNotExistException(String message, Throwable ex){
        super(message, ex);
    }
}
