package com.makurly.core.exception;

public class ExceptionDto {

    String message;

    public ExceptionDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
