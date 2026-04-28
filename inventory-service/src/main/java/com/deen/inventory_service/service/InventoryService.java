package com.deen.inventory_service.service;

import com.deen.inventory_service.entity.Inventory;
import com.deen.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public void reduceStock(Long productId,Integer quantity){
        Inventory inventory= inventoryRepository.findByProductId(productId)
                .orElseThrow(()-> new RuntimeException("Product Id not found"));

        if(inventory.getQuantity()<quantity){
            throw new RuntimeException("Not enough stock");
        }

        inventory.setQuantity(inventory.getQuantity()-quantity);
        inventoryRepository.save(inventory);

    }

}
