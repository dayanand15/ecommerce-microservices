package com.deen.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.deen.order_service.dto.ApiResponse;
import com.deen.order_service.dto.ProductResponse;

@FeignClient(name="product-service")
public interface ProductClient {

  @GetMapping("/products/{id}")
  ApiResponse<ProductResponse> getProductById(@PathVariable Long id);
  
}
