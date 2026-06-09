package com.deen.order_service.service;

import java.time.LocalDateTime;
import java.util.List;

import com.deen.order_service.client.InventoryClient;
import com.deen.order_service.dto.*;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import com.deen.order_service.client.ProductClient;
import com.deen.order_service.entity.Order;
import com.deen.order_service.repository.OrderRespository;



@Service
public class OrderService {


  private final OrderRespository orderRepository;
  private final ResilienceService resilienceService;
  private final KafkaProducerService kafkaProducerService;




  public OrderService(ProductClient productClient, OrderRespository orderRespository, InventoryClient inventoryClient, ResilienceService resilienceService,KafkaProducerService kafkaProducerService){

    this.orderRepository =orderRespository;
    this.resilienceService=resilienceService;
    this.kafkaProducerService=kafkaProducerService;
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
    //Implemented Optimistic Locking for reduceStock
    // by adding @Version in inventory entity and added @Transactional on reduceStock in inventory-service
    try{
    resilienceService.reduceStock(productId,quantity);
    }
    catch(ObjectOptimisticLockingFailureException ex){
      throw new RuntimeException(
              "Inventory update conflict. Please retry"
      );
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

    //Publish event after saving
    OrderCreatedEvent event=
            new OrderCreatedEvent(
                    savedOrder.getOrderId(),
                    savedOrder.getUserId(),
                    savedOrder.getProductId(),
                    savedOrder.getQuantity()
            );
    //publish orderCreated event
    kafkaProducerService.publishOrderCreated(event);


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

  //Implemented fetching of top 5 orders by using Cursor Pagination
  public List<OrderResponse> getOrdersTop5ByOrdersByCursor(Long lastOrderId){
        List<Order> orders= orderRepository.findTop5ByOrderIdGreaterThanOrderByOrderIdAsc(lastOrderId);

        return orders.stream().map(order -> new OrderResponse(
                order.getOrderId(),
                order.getUserId(),
                order.getProductId(),
                order.getProductName(),
                order.getQuantity(),
                "Top 5 orders fetched successfully"
        )).toList();
  }
}
