package com.deen.order_service.service;

import com.deen.order_service.client.InventoryClient;
import com.deen.order_service.client.ProductClient;
import com.deen.order_service.client.UserClient;
import com.deen.order_service.dto.ApiResponse;
import com.deen.order_service.dto.ProductResponse;
import com.deen.order_service.dto.UserResponse;
import com.deen.order_service.exception.InsufficientStockException;
import com.deen.order_service.exception.ProductNotFoundException;
import com.deen.order_service.exception.UserNotFoundException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class ResilienceService {

    private final UserClient userClient;
    private final ProductClient productClient;
    private final InventoryClient inventoryClient;

    public ResilienceService(UserClient userClient, ProductClient productClient,InventoryClient inventoryClient){
        this.userClient=userClient;
        this.productClient=productClient;
        this.inventoryClient=inventoryClient;
    }

    @CircuitBreaker(
            name="userServiceBreaker",
            fallbackMethod="userFallback"
    )
    @Retry(name="userServiceRetry")
    public UserResponse validateUser(Long userId){
        ApiResponse<UserResponse> userResponse=userClient.getUserById(userId);

        System.out.println("Calling User service");
        if (userResponse == null || userResponse.getData() == null) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
        return userResponse.getData();
    }

    public UserResponse userFallback(Long userId,Exception ex){
        System.out.println("User fallback executed");

        return UserResponse.builder()
                .name("Fallback User")
                .email("unavailable@test.com")
                .build();
    }

    @CircuitBreaker(
            name="productServiceBreaker",
            fallbackMethod="productFallback"
    )
    @Retry(name="productServiceRetry")
    public ProductResponse validateProduct(Long productId){
        System.out.println("Calling Product service");

        ApiResponse<ProductResponse> productResponse = productClient.getProductById(productId);

        if (productResponse == null || productResponse.getData() == null) {
            throw new ProductNotFoundException("Product not found with id: " + productId);
        }

        return  productResponse.getData();
    }

    public ProductResponse productFallback(Long productId,Exception ex){
        System.out.println("Product fallback executed");

        throw new RuntimeException("Product Service temporarily unavailable");
    }

    @CircuitBreaker(
            name="inventoryServiceBreaker",
            fallbackMethod="inventoryFallback"
    )
    @Retry(name="inventoryServiceRetry")
    public Boolean reduceStock(Long productId,Integer quantity){
        System.out.println("Calling Inventory service");

        Boolean isReduced = inventoryClient.reduceStock(productId, quantity);

        if (isReduced == null || !isReduced) {
            throw new InsufficientStockException("Insufficient stock for product: " + productId);
        }
        return true;
    }

    public Boolean inventoryFallback(Long productId,Integer quantity,Exception ex){
        System.out.println("Inventory fallback executed");
        throw new RuntimeException("Inventory service temporarily unavailable");
    }
}
