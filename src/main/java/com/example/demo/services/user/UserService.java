package com.example.demo.services.user;

import java.util.Optional;

import com.example.demo.config.ModifiedUserDetails;
import com.example.demo.dto.user.UserRequest;
import com.example.demo.dto.user.UserResponse;
import com.example.demo.models.User;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
    UserResponse getUser(Long id);
    User getUserByName(String name);
    UserResponse getUserResponseByName(String name);
    // UserResponse editUser(UserRequest userRequest);
    void deleteUser(Long id);
    boolean existsUserByEmail(String email);    
    String getCurrentUserName();
    Optional<User> getCurrentUser();
    Optional<ModifiedUserDetails> getCurrentUserDetails();
}