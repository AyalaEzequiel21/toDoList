package com.example.TodoList.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseException> handleResourceNotFoundException(ResourceNotFoundException ex){
        ErrorResponseException errorResponse = new ErrorResponseException(HttpStatus.NOT_FOUND, ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceRepeatException.class)
    public ResponseEntity<ErrorResponseException> handleResourceRepeatException(ResourceRepeatException ex){
        ErrorResponseException errorResponse = new ErrorResponseException(HttpStatus.ALREADY_REPORTED, ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseException> handleBadRequestException(BadRequestException ex){
        ErrorResponseException errorResponse = new ErrorResponseException(HttpStatus.BAD_REQUEST, ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
