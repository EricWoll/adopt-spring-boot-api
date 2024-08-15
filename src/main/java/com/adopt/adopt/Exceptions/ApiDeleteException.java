package com.adopt.adopt.Exceptions;

public class ApiDeleteException extends RuntimeException {

    public ApiDeleteException(String message) {
        super(message);
    }

    public ApiDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
