package com.deen.order_service.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deen.order_service.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

  private final OrderService orderService;
  
  public OrderController(OrderService orderService){
    this.orderService=orderService;
  }

  @PostMapping("/{productId}")
  public String createOrder(@PathVariable Long productId){
    return orderService.createOrder(productId);
  }
}
