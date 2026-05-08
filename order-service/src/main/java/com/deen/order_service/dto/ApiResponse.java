package com.deen.order_service.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

  private LocalDateTime timestamp;
  private Integer status;
  private boolean success;
  private String message;
  private T data;

}
