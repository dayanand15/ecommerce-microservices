package com.deen.order_service.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name ="orders")
@Data
@RequiredArgsConstructor
public class Order {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long orderId;

  //FOREIGN KEYS
  private Long userId;
  private Long productId;

  private String productName;
  private Integer quantity;

  private LocalDateTime createdAt; 

}
