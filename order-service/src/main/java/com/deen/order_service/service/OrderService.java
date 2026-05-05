package com.deen.order_service.service;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import com.deen.order_service.client.ProductClient;
import com.deen.order_service.client.UserClient;
import com.deen.order_service.dto.ApiResponse;
import com.deen.order_service.dto.OrderRequest;
import com.deen.order_service.dto.ProductResponse;
import com.deen.order_service.dto.UserResponse;
import com.deen.order_service.entity.Order;
import com.deen.order_service.exception.ProductNotFoundException;
import com.deen.order_service.exception.UserNotFoundException;
import com.deen.order_service.repository.OrderRespository;


@Service
public class OrderService {

  private final ProductClient productClient;
  private final UserClient userClient;
  private final OrderRespository orderRespository;
  


  public OrderService(ProductClient productClient,UserClient userClient,OrderRespository orderRespository){
    this.productClient=productClient;
    this.userClient=userClient;
    this.orderRespository=orderRespository;
  }

  public ApiResponse<Long> createOrder(OrderRequest orderRequest){

    Long userId = orderRequest.getUserId();
    Long productId = orderRequest.getProductId();
    Integer quantity = orderRequest.getQuantity();

    //Validate User
    ApiResponse<UserResponse> userResponse=userClient.getUserById(userId);
    UserResponse user=userResponse.getData();

    if(user==null){
      throw new UserNotFoundException("User not found");
    }
    //Validate Product
    ApiResponse<ProductResponse> productResponse=productClient.getProductById(productId);
    ProductResponse product=productResponse.getData();

    if(product==null){
      throw new ProductNotFoundException("Product not found");
    }

    Order order = new Order();
    order.setUserId(userId);
    order.setProductId(productId);
    order.setProductName(product.getName());
    order.setQuanaity(quantity !=null ? quantity : 1);
    order.setCreatedAt(LocalDateTime.now());

    orderRespository.save(order);

    return new ApiResponse<Long>
    (LocalDateTime.now(),
    201,
    "Order created successfully",
    order.getOrderId()
    );

  }

}
