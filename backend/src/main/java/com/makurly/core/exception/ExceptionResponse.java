package com.makurly.core.exception;

public class ExceptionResponse {

    private final String message;

    public ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
