package com.deen.order_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderRequest {

  @NotNull(message = "UserId is required")
  private Long userId;
  
  @NotNull(message = "ProductId is required")
  private Long productId;

  @Min(value = 1, message="Quantity must be at least 1")
  private Integer quantity;
}
