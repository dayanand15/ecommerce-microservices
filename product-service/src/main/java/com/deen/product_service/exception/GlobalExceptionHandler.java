package com.deen.product_service.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity <ErrorResponse> handleProductNotFound(ProductNotFoundException ex){
      
     ErrorResponse error=new ErrorResponse(
      LocalDateTime.now(),
      HttpStatus.NOT_FOUND.value(),
      "Not Found",
      ex.getMessage(),
      null);

      return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity <ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex){

     List<FieldErrorResponse> fieldErrors=ex.getBindingResult()
     .getFieldErrors()
     .stream()
     .map(error -> new FieldErrorResponse(
              error.getField(),
              error.getDefaultMessage()
     ))
     .toList();

      ErrorResponse errorResponse= new ErrorResponse(
        LocalDateTime.now(),
        HttpStatus.BAD_REQUEST.value(),
        "Bad Request", 
        "Validation failed",
        fieldErrors
        );

      return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}


