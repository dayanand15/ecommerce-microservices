package com.deen.inventory_service.repository;

import com.deen.inventory_service.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Long>    {

    Inventory findByProductId(Long productId);
}
