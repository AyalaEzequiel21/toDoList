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

    public ResponseEntity<ErrorResponseException> handleResourceRepeatException(ResourseRepeatException ex){
        ErrorResponseException errorResponse = new ErrorResponseException(HttpStatus.ALREADY_REPORTED, ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.ALREADY_REPORTED);
    }

    public ResponseEntity<ErrorResponseException> handleBadRequestException(BadRequestException ex){
        ErrorResponseException errorResponse = new ErrorResponseException(HttpStatus.BAD_REQUEST, ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
