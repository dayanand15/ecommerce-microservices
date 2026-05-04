package com.deen.inventory_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InventoryResponse {

    private Long inventoryId;

    private Long productId;

    private Integer quantity;

}
