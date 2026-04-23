package com.deen.order_service.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {

  private LocalDateTime timestamp;
  private boolean success;
  private String message;
  private T data;

}
