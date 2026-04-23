package com.deen.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResponse {

  private Long orderId;
  private Long userId;
  private Long productId;
  private Long productName;
  private Long quantity;

}
