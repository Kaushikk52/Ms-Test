package com.example.user.controllers;

import com.example.commons.constants.EventType;
import com.example.commons.events.UserEvent;
import com.example.commons.models.GenericResponse;
import com.example.commons.response.ResponseBuilder;
import com.example.user.models.User;
import com.example.user.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/api/users")
public class UserController {

    private final UserService userServ;
    private final StreamBridge streamBridge;

    // CREATE - SAGA INITIATION
    @PostMapping(value = "/register")
    public ResponseEntity<GenericResponse<User>> createUser(@RequestBody User user) {
        User savedUser = userServ.register(user);
        // Publish event to trigger the next step in the saga
        streamBridge.send("createUser-out-0", new UserEvent(savedUser.getId(),savedUser.getUsername(),savedUser.getEmail(),EventType.CREATED));
        return ResponseEntity.ok(ResponseBuilder.success(
                        savedUser,
                        "User creation initiated. Awaiting saga completion"));
    }


    @GetMapping(value="/{id}")
    public ResponseEntity<GenericResponse<User>> getUserById(@PathVariable String id) {
        Optional<User> user = userServ.getUserById(id);
        return ResponseEntity.ok(user.map(ResponseBuilder::success)
                        .orElse(ResponseBuilder.failure("User Not Found")));
    }

    @GetMapping(value="/all")
    public ResponseEntity<GenericResponse<List<User>>> getAllUsers() {
        return ResponseEntity.ok(ResponseBuilder.success(userServ.getAllUsers(),"Retrieved All Users"));
    }

//    @PostMapping(value = "/edit")
//    public ResponseEntity<Map<String,Object>> edit(@RequestBody User user){
//        Map<String,Object> response = new HashMap<>();
//        try{
//            User updatedUser = userServ.updateUser(user);
//            log.info("User updated Successfully : {}",updatedUser.getId());
//            response.put("message","User updated Successfully");
//            response.put("user",updatedUser);
//            return ResponseEntity.status(HttpStatus.OK).body(response);
//        }catch (Exception e) {
//            log.warn("An Error occurred : {}", e.getMessage());
//            response.put("message",e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
//
//    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<GenericResponse<String>> deleteUser(@PathVariable String id) {
        userServ.deleteUser(id);
        // Publish event to trigger the saga (delete related data in other services)
        streamBridge.send("deleteUser-out-0", new UserEvent(id,null,null ,EventType.CREATED));
        return ResponseEntity.ok(ResponseBuilder.success("User deletion initiated."));
    }
}
