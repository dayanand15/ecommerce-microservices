package com.deen.order_service.exception;

import com.deen.order_service.dto.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private <T> ResponseEntity<ApiResponse<T>> buildResponse(String message, HttpStatus status) {
    ApiResponse<T> response = new ApiResponse<>(
            LocalDateTime.now(),
            status.value(),
            false,
            message,
            null
    );

    return new ResponseEntity<>(response, status);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ApiResponse<Object>> handleUser(UserNotFoundException ex) {
    return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<ApiResponse<Object>> handleProduct(ProductNotFoundException ex) {
    return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(InsufficientStockException.class)
  public ResponseEntity<ApiResponse<Object>> handleStock(InsufficientStockException ex) {
    return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<Object>> handleGeneric(Exception ex) {
    return buildResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}