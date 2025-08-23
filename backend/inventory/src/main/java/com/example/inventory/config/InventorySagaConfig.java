package com.example.inventory.config;

import com.example.inventory.repositories.InventoryRepo;
import com.example.inventory.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
public class InventorySagaConfig {

    private InventoryService invServ;

    @Bean
    public Function<UserCreatedEvent,Object> processUser(){
        return event -> {
            try {
                invServ.handleUserCreated();
                return new InventoryReservedEvent(event.getUserId());
            } catch (Exception e) {
                return new InventoryReservationFailedEvent(event.getUserId(), "Out of stock");
            }
        }
    }
}
