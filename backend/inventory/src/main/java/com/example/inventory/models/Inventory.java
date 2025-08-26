package com.example.inventory.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", nullable = false, updatable = false, length = 36)
    private String id;
    @Column(name = "itemName")
    private String itemName;
    @Column(name = "stock")
    private int stock;
}
