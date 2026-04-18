package com.deen.product_service.exception;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private LocalDateTime Timestamp;
    private int status;
    private String error;
    private String message;
    private List<FieldErrorResponse> errors;
  }
