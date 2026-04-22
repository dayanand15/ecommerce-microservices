package com.deen.order_service.service;

import org.springframework.stereotype.Service;

import com.deen.order_service.client.ProductClient;
import com.deen.order_service.dto.ApiResponse;
import com.deen.order_service.dto.ProductResponse;

@Service
public class OrderService {

  private final ProductClient productClient;


  public OrderService(ProductClient productClient){
    this.productClient=productClient;
  }



  public String createOrder(Long productId){
    
    ApiResponse<ProductResponse> response=productClient.getProductById(productId);
    ProductResponse product =response.getData();

    return "Order created for product: "+product.getName();
  }


}
