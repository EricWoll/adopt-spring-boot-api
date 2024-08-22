package com.adopt.adopt.Exception;

import com.adopt.adopt.Exception.CustomExceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {
            AnimalNotFoundException.class,
            AdoptionRecordNotFoundException.class,
            UserExistsException.class,
            UserNotFoundException.class,
            AdoptionRecordExistsException.class
    })
    public ResponseEntity<Object> handleApiExceptions(Exception e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(
                e.getMessage(),
                e,
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, badRequest);
    }


}
