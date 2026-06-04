package com.example.demo.service;

import com.example.demo.entity.UserEntity;

public interface UserService {
    UserEntity createUser(UserEntity user);
    UserEntity getUserById(Long id);
    void deleteUser(Long id);
    boolean isValid(Long id);
}
