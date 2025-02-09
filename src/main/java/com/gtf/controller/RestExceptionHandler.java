package com.gtf.controller;

import com.gtf.response.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse> badRequestException(MethodArgumentNotValidException ex) {
        HashMap<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        ApiResponse response = new ApiResponse("ERROR", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

  /*  @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation ->
                errors.put(violation.getPropertyPath().toString(), violation.getMessage())
        );

        ApiResponse response = new ApiResponse("ERROR", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }*/


}
