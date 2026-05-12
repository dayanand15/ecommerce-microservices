package com.deen.order_service.service;

import java.time.LocalDateTime;

import com.deen.order_service.client.InventoryClient;
import com.deen.order_service.dto.*;
import org.springframework.stereotype.Service;
import com.deen.order_service.client.ProductClient;
import com.deen.order_service.entity.Order;
import com.deen.order_service.repository.OrderRespository;



@Service
public class OrderService {


  private final OrderRespository orderRepository;
  private final ResilienceService resilienceService;




  public OrderService(ProductClient productClient, OrderRespository orderRespository, InventoryClient inventoryClient, ResilienceService resilienceService){

    this.orderRepository =orderRespository;
    this.resilienceService=resilienceService;
  }

  public OrderResponse createOrder(OrderRequest orderRequest) {
    Long userId = orderRequest.getUserId();
    Long productId = orderRequest.getProductId();
    Integer quantity = orderRequest.getQuantity();


    // Validate User
    resilienceService.validateUser(userId);


    // Validate Product
    ProductResponse product = resilienceService.validateProduct(productId);


    //  Reduce Stock (IMPORTANT — only once)
    resilienceService.reduceStock(productId,quantity);


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
            savedOrder.getQuantity(),
            "Order Created Successfully"
    );
  }
}
