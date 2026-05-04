package com.deen.order_service.service;

import java.time.LocalDateTime;

import com.deen.order_service.client.InventoryClient;
import com.deen.order_service.dto.*;
import com.deen.order_service.exception.InsufficientStockException;
import org.springframework.stereotype.Service;
import com.deen.order_service.client.ProductClient;
import com.deen.order_service.client.UserClient;
import com.deen.order_service.entity.Order;
import com.deen.order_service.exception.ProductNotFoundException;
import com.deen.order_service.exception.UserNotFoundException;
import com.deen.order_service.repository.OrderRespository;


@Service
public class OrderService {

  private final ProductClient productClient;
  private final UserClient userClient;
  private final InventoryClient inventoryClient;
  private final OrderRespository orderRespository;
  


  public OrderService(ProductClient productClient,UserClient userClient,OrderRespository orderRespository,InventoryClient inventoryClient){
    this.productClient=productClient;
    this.userClient=userClient;
    this.inventoryClient=inventoryClient;
    this.orderRespository=orderRespository;
  }

  public OrderResponse createOrder(OrderRequest orderRequest){

    Long userId = orderRequest.getUserId();
    Long productId = orderRequest.getProductId();
    Integer quantity = orderRequest.getQuantity();

    //Validate User
      ApiResponse<UserResponse> userResponse = userClient.getUserById(userId);

      UserResponse user=userResponse.getData();

      if (user==null) {
        throw new UserNotFoundException("User not found in order service flow");
      }

    //Validate Product

    ApiResponse<ProductResponse> productResponse=productClient.getProductById(productId);

    ProductResponse product=productResponse.getData();
    if(product==null) {
      throw new ProductNotFoundException("Product not found in order flow");
    }

    //Validate for quantity
    Boolean isReduced = inventoryClient.reduceStock(orderRequest.getProductId(),orderRequest.getQuantity());

   if(!isReduced){
     throw new InsufficientStockException("Insufficient stock for product "+orderRequest.getProductId());
   }


    Order order = new Order();
    order.setUserId(orderRequest.getUserId());
    order.setProductId(orderRequest.getProductId());
    order.setProductName(product.getName());
    order.setQuanaity(quantity);
    order.setCreatedAt(LocalDateTime.now());

    Order saved=orderRespository.save(order);

    return new OrderResponse(
            saved.getOrderId(),
            saved.getUserId(),
            saved.getProductId(),
            saved.getProductName(),
            saved.getQuanaity()
            );
  }
}
