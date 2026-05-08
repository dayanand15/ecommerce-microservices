package com.deen.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

  private Long orderId;
  private Long userId;
  private Long productId;
  private String productName;
  private Integer quantity;

}
