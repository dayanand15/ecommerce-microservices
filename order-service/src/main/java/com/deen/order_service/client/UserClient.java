package com.deen.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.deen.order_service.dto.UserResponse;
import com.deen.order_service.dto.ApiResponse;

@FeignClient(name = "user-service")
public interface UserClient {

  @GetMapping("/users/{id}")
  ApiResponse<UserResponse> getUserById(@PathVariable Long id);
}
