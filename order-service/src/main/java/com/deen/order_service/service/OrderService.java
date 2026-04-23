package com.deen.order_service.service;

import org.springframework.stereotype.Service;

import com.deen.order_service.client.ProductClient;
import com.deen.order_service.client.UserClient;
import com.deen.order_service.dto.ApiResponse;
import com.deen.order_service.dto.ProductResponse;
import com.deen.order_service.dto.UserResponse;


@Service
public class OrderService {

  private final ProductClient productClient;
  private final UserClient userClient;
  


  public OrderService(ProductClient productClient,UserClient userClient){
    this.productClient=productClient;
    this.userClient=userClient;
  }

  public String createOrder(Long productId,Long userId){

    //CALL USER SERVICE

    ApiResponse<UserResponse> userResponse=userClient.getUserById(userId);
    UserResponse user=userResponse.getData();
    

    //CALL PRODUCT SERVICE
    ApiResponse<ProductResponse> ProductResponse=productClient.getProductById(productId);
    ProductResponse product =ProductResponse.getData();

    if(product == null){
      throw new RuntimeException("Product not found");
    }

    return "Order created for user: "+user.getName()+ " and product: "+product.getName();
  }


}
