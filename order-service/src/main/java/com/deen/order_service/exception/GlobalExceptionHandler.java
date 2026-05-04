package com.deen.order_service.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.deen.order_service.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ApiResponse<String>> handleUserNotFound(UserNotFoundException ex){

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body(new ApiResponse<>(LocalDateTime.now(),404,ex.getMessage(),null));
  }

  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<ApiResponse<String>> handleProductNotFound(ProductNotFoundException ex){

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body(new ApiResponse<>(LocalDateTime.now(),404,ex.getMessage(),null));
  }

  @ExceptionHandler(InsufficientStockException.class)
  public ResponseEntity<ApiResponse<String>> handleInsufficientStock(InsufficientStockException ex){

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ApiResponse<>(LocalDateTime.now(),404,ex.getMessage(),null));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<String>> handleMethodArgumentNotFound(MethodArgumentNotValidException ex){

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
              .body(new ApiResponse<>(LocalDateTime.now(),400,ex.getMessage(),null));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<String>> handleGeneric(Exception ex){

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(new ApiResponse<>(LocalDateTime.now(),500,ex.getMessage(),null));
  }

}
