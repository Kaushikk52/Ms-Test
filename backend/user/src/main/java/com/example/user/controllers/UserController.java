package com.example.user.controllers;

import com.example.user.models.User;
import com.example.user.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/api/users")
public class UserController {

    private final UserService userServ;
    private final StreamBridge streamBridge;

    @PostMapping(value = "/register")
    public ResponseEntity<Map<String,Object>> register(@RequestBody User user){
        Map<String,Object> response = new HashMap<>();
        try{
            User savedUser = userServ.register(user);
            log.info("User registered successfully");
            response.put("message","User registered successfully");
            response.put("user",savedUser);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            log.warn("An Error occurred : {}", e.getMessage());
            response.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

//    @PostMapping(value = "/create")
//    public ResponseEntity<Map<String,Object>> create(@RequestBody User user){
//        Map<String,Object> response = new HashMap<>();
//        try{
//            streamBridge.send("create-user-out-0",user);
//            log.info("User registered successfully");
//            response.put("message","User registered successfully");
//            return ResponseEntity.status(HttpStatus.OK).body(response);
//        } catch (Exception e) {
//            log.warn("An Error occurred : {}", e.getMessage());
//            response.put("message",e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
//    }

//    @GetMapping(value = "/all")
//    public ResponseEntity<Map<String,Object>> getAll(){
//        Map<String,Object> response = new HashMap<>();
//        try {
//            List<User> users = userServ.getAll();
//            log.info("Retrieved all users : {}",users.size());
//            response.put("message","Retrieved all users : "+users.size());
//            response.put("users",users);
//            return ResponseEntity.status(HttpStatus.OK).body(response);
//        } catch (Exception e) {
//            log.warn("An Error occurred : {}", e.getMessage());
//            response.put("message",e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
//    }
//
//    @GetMapping(value = "/{id}")
//    public ResponseEntity<Map<String,Object>> getById(@PathVariable Long id){
//        Map<String,Object> response = new HashMap<>();
//        try {
//            User user = userServ.getById(id);
//            log.info("Retrieved User By Id:{}",user.getId());
//            response.put("message","Retrieved User By Id : "+user.getId());
//            response.put("user",user);
//            return ResponseEntity.status(HttpStatus.OK).body(response);
//        }catch (RuntimeException e) {
//            log.warn("A RuntimeException occurred : {}", e.getMessage());
//            response.put("message",e.getMessage());
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//        } catch (Exception e) {
//            log.warn("An Error occurred : {}", e.getMessage());
//            response.put("message",e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
//    }
//
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
//
//    @PostMapping(value = "/delete/{id}")
//    public ResponseEntity<Map<String,Object>> deleteById(@PathVariable Long id){
//        Map<String,Object> response = new HashMap<>();
//        try{
//            userServ.deleteUser(id);
//            log.info("User deleted successfully : {}",id);
//            response.put("message","User deleted Successfully");
//            return  ResponseEntity.status(HttpStatus.OK).body(response);
//        }catch (Exception e) {
//            log.warn("An Error occurred : {}", e.getMessage());
//            response.put("message",e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
//    }
}
