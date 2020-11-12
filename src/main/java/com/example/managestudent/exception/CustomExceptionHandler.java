package com.example.managestudent.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{
    private String INCORRECT_REQUEST = "INCORRECT_REQUEST";
    private String BAD_REQUEST = "BAD_REQUEST";

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException
            (RecordNotFoundException ex)
    {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ErrorResponse error = new ErrorResponse(INCORRECT_REQUEST, details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingHeaderInfoException.class)
    public final ResponseEntity<ErrorResponse> handleInvalidTraceIdException
            (MissingHeaderInfoException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ErrorResponse error = new ErrorResponse(BAD_REQUEST, details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}