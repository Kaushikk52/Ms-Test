package com.example.commons.events;

import com.example.commons.constants.EventType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@Data
public class UserEvent implements Serializable {
    private String userId;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

}
