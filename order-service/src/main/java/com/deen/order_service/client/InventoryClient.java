package com.deen.order_service.client;

import com.deen.order_service.dto.ApiResponse;
import com.deen.order_service.dto.InventoryResponse;
import com.deen.order_service.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryClient {

    @PutMapping ("/inventory/reduce-stock")
    public Boolean  reduceStock(@RequestParam Long productId, @RequestParam Integer quantity);
}

