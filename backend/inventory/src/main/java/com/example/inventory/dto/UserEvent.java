package com.example.inventory.dto;

import com.example.inventory.constants.EventType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEvent {
    private String id;
    private String userName;
    private String email;
    @Enumerated(EnumType.STRING)
    private EventType eventType;
}
