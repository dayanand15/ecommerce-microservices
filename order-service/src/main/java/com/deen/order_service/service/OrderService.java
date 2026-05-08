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
  private final OrderRespository orderRepository;
  private final InventoryClient inventoryClient;



  public OrderService(ProductClient productClient, UserClient userClient, OrderRespository orderRespository, InventoryClient inventoryClient){
    this.productClient=productClient;
    this.userClient=userClient;
    this.orderRepository =orderRespository;
    this.inventoryClient=inventoryClient;
  }

  public OrderResponse createOrder(OrderRequest orderRequest) {

    Long userId = orderRequest.getUserId();
    Long productId = orderRequest.getProductId();
    Integer quantity = orderRequest.getQuantity();

    // Validate User
    ApiResponse<UserResponse> userResponse = userClient.getUserById(userId);
    System.out.println("USER-RESPONSE: "+userResponse);

    if (userResponse == null || userResponse.getData() == null) {
      throw new UserNotFoundException("User not found with id: " + userId);
    }

    UserResponse user = userResponse.getData();


    // Validate Product
    ApiResponse<ProductResponse> productResponse = productClient.getProductById(productId);

    if (productResponse == null || productResponse.getData() == null) {
      throw new ProductNotFoundException("Product not found with id: " + productId);
    }

    ProductResponse product = productResponse.getData();


    //  Reduce Stock (IMPORTANT — only once)
    Boolean isReduced = inventoryClient.reduceStock(productId, quantity);

    if (isReduced == null || !isReduced) {
      throw new InsufficientStockException("Insufficient stock for product: " + productId);
    }


    //  Create Order Entity
    Order order = new Order();
    order.setUserId(userId);
    order.setProductId(productId);
    order.setProductName(product.getName());
    order.setQuantity(quantity);
    order.setCreatedAt(LocalDateTime.now());


    //  Save Order
    Order savedOrder = orderRepository.save(order);


    // Return Response
    return new OrderResponse(
            savedOrder.getOrderId(),
            savedOrder.getUserId(),
            savedOrder.getProductId(),
            savedOrder.getProductName(),
            savedOrder.getQuantity()
    );
  }
}
