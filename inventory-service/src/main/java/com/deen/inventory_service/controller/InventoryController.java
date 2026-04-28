package com.deen.inventory_service.controller;

import com.deen.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/reduce-stock")
    public String reduceStock(@RequestParam Long productId,
                              @RequestParam Integer quantity){
        inventoryService.reduceStock(productId,quantity);
        return "Stock reduced successfully";
    }
}
