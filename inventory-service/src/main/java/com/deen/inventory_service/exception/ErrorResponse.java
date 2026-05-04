package com.deen.inventory_service.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private LocalDateTime Timestamp;
    private int status;
    private String error;
    private String message;
    private List<FieldErrorResponse> errors;
}