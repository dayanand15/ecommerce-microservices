package com.deen.order_service.client;

//import com.deen.order_service.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

    @PutMapping("/inventory/reduce-stock")
    Boolean reduceStock(
            @RequestParam("productId") Long productId,
            @RequestParam("quantity") Integer quantity
    );
}
