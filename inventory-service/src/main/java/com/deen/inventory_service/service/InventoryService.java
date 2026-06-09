package com.deen.inventory_service.service;

import com.deen.inventory_service.entity.Inventory;
import com.deen.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional
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
