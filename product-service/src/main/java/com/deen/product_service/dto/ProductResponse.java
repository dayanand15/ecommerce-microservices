package com.deen.product_service.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponse {
  
  private Long product_id;
  private String name;
  private String description;
  private BigDecimal price;
  private Integer stockQuantity;
  private String category;
}
