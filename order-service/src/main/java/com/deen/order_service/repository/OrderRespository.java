package com.deen.order_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deen.order_service.entity.Order;

public interface OrderRespository extends JpaRepository<Order,Long>{

  List<Order> findByUserId(Long userId);

  List<Order> findTop5ByOrderIdGreaterThanOrderByOrderIdAsc(Long OrderId);
  //Derived Spring Data JPA understands: find(Select Query),Top5(LIMIT 5),By(Where clause starts),OrderIdGreaterThan(order_id>)
  //OrderByIdAsc(ORDER BY order_id ASC)
}
