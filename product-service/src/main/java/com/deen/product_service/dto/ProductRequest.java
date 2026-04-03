package com.deen.product_service.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductRequest {
 
  private String name;
  private String description;
  private BigDecimal price;
  private Integer stockQuantity;
  private String category;
}
