package com.example.inventory.config;

import com.example.inventory.dto.UserEvent;
import com.example.inventory.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
public class InventoryFunctions {
    // This function acts as the next step in the Saga for user creation
    @Bean
    public Consumer<UserEvent> handleUserCreation(InventoryService inventoryServ) {
        return event -> {
            System.out.println("Saga: Inventory service received UserCreatedEvent for user ID: " + event.getId());
            inventoryServ.handleUserCreated(event.getId());
            System.out.println("Saga: Inventory successfully updated for user ID: " + event.getId());
        };
    }
}
