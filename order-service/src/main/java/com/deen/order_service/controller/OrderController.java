package com.deen.order_service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deen.order_service.dto.ApiResponse;
import com.deen.order_service.dto.OrderRequest;
import com.deen.order_service.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

  private final OrderService orderService;
  
  public OrderController(OrderService orderService){
    this.orderService=orderService;
  }

  @PostMapping
  public ApiResponse<Long> createOrder(@RequestBody OrderRequest orderRequest){
    return orderService.createOrder(orderRequest);
  }

  
}
