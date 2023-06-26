package com.example.To.Do.list.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class RestExceptionHandler {

    public ResponseEntity<ErrorResponseException> handleResourceNotFoundException(ResourceNotFoundException ex){
        ErrorResponseException errorResponse = new ErrorResponseException(HttpStatus.NOT_FOUND, ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
