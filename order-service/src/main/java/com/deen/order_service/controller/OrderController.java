package com.deen.order_service.controller;

import com.deen.order_service.dto.OrderResponse;
import com.deen.order_service.entity.Order;
import org.springframework.web.bind.annotation.*;

import com.deen.order_service.dto.ApiResponse;
import com.deen.order_service.dto.OrderRequest;
import com.deen.order_service.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

  private final OrderService orderService;
  
  public OrderController(OrderService orderService){
    this.orderService=orderService;
  }


  @PostMapping("/create")
  public ApiResponse<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest){
     OrderResponse orderResponse=orderService.createOrder(orderRequest);

    return new ApiResponse<>(
            LocalDateTime.now(),
            201,
            true,
            "Order created successfully",
            orderResponse
    );
  }

  @GetMapping("/cursor")
  public ApiResponse<List<OrderResponse>>  getOrdersAfterCursor(@RequestParam Long lastOrderId){

    List<OrderResponse> orders=orderService.getOrdersTop5ByOrdersByCursor(lastOrderId);
    return new ApiResponse<>(
            LocalDateTime.now(),
            200,
            true,
            "Top 5 orders fetched successfully",
            orders
    );
  }

  
}
