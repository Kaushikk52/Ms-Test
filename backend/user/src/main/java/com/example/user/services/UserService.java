package com.example.user.services;

import com.example.commons.constants.EventType;
import com.example.commons.events.UserEvent;
import com.example.commons.exceptions.NotFoundException;
import com.example.user.models.User;
import com.example.user.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final StreamBridge streamBridge;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user;
    }

    public User register(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepo.save(user);
        UserEvent event = new UserEvent(user.getId(), user.getName(), user.getEmail(), EventType.CREATED);
        streamBridge.send("userCreated-out-0",event);
        return savedUser;
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User getUserById(String id){
        return userRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public void deleteUser(String id){
        User user = this.getUserById(id);
        userRepo.delete(user);
    }

}
