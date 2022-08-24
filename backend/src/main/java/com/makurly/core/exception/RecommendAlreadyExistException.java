package com.makurly.core.exception;

public class RecommendAlreadyExistException extends RuntimeException {

    public RecommendAlreadyExistException() {
        super();
    }

    public RecommendAlreadyExistException(String message) {
        super(message);
    }

    public RecommendAlreadyExistException(Throwable ex) {
        super(ex);
    }

    public RecommendAlreadyExistException(String message, Throwable ex) {
        super(message, ex);
    }
}
