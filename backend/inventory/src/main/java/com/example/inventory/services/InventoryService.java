package com.example.inventory.services;

import com.example.inventory.dto.UserEvent;
import com.example.inventory.models.Inventory;
import com.example.inventory.repositories.InventoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepo inventoryRepo;

    public void handleUserCreated(String id){
        Inventory inventory = Inventory.builder()
                .id(id)
                .itemName("default")
                .stock(0)
                .build();
        inventoryRepo.save(inventory);
    }
}
