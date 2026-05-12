package com.deen.inventory_service.service;

import com.deen.inventory_service.dto.InventoryResponse;
import com.deen.inventory_service.entity.Inventory;
import com.deen.inventory_service.exception.InsufficientStockException;
import com.deen.inventory_service.exception.ProductNotFoundException;
import com.deen.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public Boolean reduceStock(Long productId, Integer quantity){
        Inventory inventory= inventoryRepository.findByProductId(productId);

        if(inventory ==  null || inventory.getQuantity() < quantity){
            return false;
        }
        System.out.println("REDUCE STOCK CALLED");
        inventory.setQuantity(inventory.getQuantity()-quantity);
        Inventory saved=inventoryRepository.save(inventory);

        return true;

    }

}
