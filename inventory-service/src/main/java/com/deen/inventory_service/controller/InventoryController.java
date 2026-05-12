package com.deen.inventory_service.controller;

import com.deen.inventory_service.dto.ApiResponse;
import com.deen.inventory_service.dto.InventoryResponse;
import com.deen.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PutMapping("/reduce")
    public Boolean reduceStock(@RequestParam Long productId,
                               @RequestParam Integer quantity){
        return inventoryService.reduceStock(productId,quantity);

    }
}
