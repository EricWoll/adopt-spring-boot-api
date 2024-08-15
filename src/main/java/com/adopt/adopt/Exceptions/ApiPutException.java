package com.adopt.adopt.Exceptions;

public class ApiPutException extends RuntimeException {

    public ApiPutException(String message) {
        super(message);
    }

    public ApiPutException(String message, Throwable cause) {
        super(message, cause);
    }
}
