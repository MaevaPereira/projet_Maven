package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public UserEntity createUser(@RequestBody UserEntity user) {
        return userService.createUser(user);
    }

    @GetMapping("/users/{id}")
    public UserEntity getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @GetMapping("/users/{id}/valid")
    public boolean isValid(@PathVariable Long id){
        return userService.isValid(id);
    }

}
