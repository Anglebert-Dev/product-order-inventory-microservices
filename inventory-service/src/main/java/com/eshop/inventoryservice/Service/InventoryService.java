package com.eshop.inventoryservice.Service;

import com.eshop.inventoryservice.Model.Inventory;
import com.eshop.inventoryservice.Repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional
    public boolean isInStock(String skuCode) {
        return  inventoryRepository.findBySkuCode(skuCode).isPresent();
    }
}
