package com.deen.product_service.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductRequest {

 @NotBlank(message="Product name must not be empty")
  private String name;

  private String description;

  @NotNull(message="Product price should not be null")
  @Positive(message="Product price should be greater than 0")
  private BigDecimal price;

  @Positive(message="Product quantity should not be negative or 0")
  private Integer stockQuantity;

  @NotBlank(message = "category must not be empty")
  private String category;
}
