package com.learning.inventorysvc.repository;

import com.learning.inventorysvc.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    Optional<Inventory> findBySkuCodeIn(List<String> skuCodes);
}
