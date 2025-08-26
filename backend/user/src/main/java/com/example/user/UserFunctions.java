package com.example.user;

import com.example.commons.events.UserEvent;
import com.example.user.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class UserFunctions {
    @Bean
    public Consumer<UserEvent> rollbackUser(UserService userServ) {
        return event -> {
            System.out.println("Saga: Received rollback event for user ID: " + event.getUserId() + ". Deleting user.");
            userServ.deleteUser(event.getUserId());
        };
    }

}
