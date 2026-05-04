package com.deen.order_service.controller;

import com.deen.order_service.dto.OrderResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deen.order_service.dto.ApiResponse;
import com.deen.order_service.dto.OrderRequest;
import com.deen.order_service.service.OrderService;

import java.time.LocalDateTime;

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
            "Order created successfully",
            orderResponse
    );
  }

  
}
