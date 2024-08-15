package com.adopt.adopt.Exceptions;

public class ApiPostException extends RuntimeException {

    public ApiPostException(String message) {
        super(message);
    }

    public ApiPostException(String message, Throwable cause) {
        super(message, cause);
    }
}
