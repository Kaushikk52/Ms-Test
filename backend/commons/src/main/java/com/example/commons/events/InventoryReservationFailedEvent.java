package com.example.commons.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class InventoryReservationFailedEvent implements Serializable {
    private Long userId;
    private String reason;
}
