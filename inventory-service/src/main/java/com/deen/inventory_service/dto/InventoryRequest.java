package com.deen.inventory_service.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class InventoryRequest {

    @Positive(message = "Product quantity should not be negative or 0")
    private Integer quantity;
}
