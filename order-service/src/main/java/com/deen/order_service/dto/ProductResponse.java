package com.deen.order_service.dto;

import java.math.BigDecimal;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponse {

  private Long productId;
  private String name;
  private BigDecimal price;
}
