package com.example.user.services;

import com.example.commons.events.UserEvent;
import com.example.user.models.User;
import com.example.user.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final StreamBridge streamBridge;

    public User register(User user){
        User savedUser = userRepo.save(user);
        UserEvent event = new UserEvent(user.getId(), user.getName(), user.getEmail(),"CREATED");
        streamBridge.send("userCreated-out-0",event);
        return savedUser;
    }

}
