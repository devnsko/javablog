package com.example.demo.services.user;

import java.util.List;

import com.example.demo.dto.user.UserRequest;
import com.example.demo.dto.user.UserResponse;
import com.example.demo.models.User;

public interface UserService {
    UserResponse getUser(Long id);
    List<UserResponse> getAllUsers();
    UserResponse getUserByName(String name);
    UserResponse getUserByEmail(String email);
    UserResponse editUser(UserRequest userRequest);
    void deleteUser(Long id);
    boolean existsUserByEmail(String email);
    User getCurrentUser();
}