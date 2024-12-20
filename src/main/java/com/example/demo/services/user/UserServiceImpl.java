package com.example.demo.services.user;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.user.UserRequest;
import com.example.demo.dto.user.UserResponse;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.mappers.UserMapper;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    @Override
    public UserResponse getUser(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No user found with id: " + id));
        UserResponse userResponse = userMapper.toUserResponse(user);
        return userResponse;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = userMapper.toUserResponses(users);
        return userResponses;
    }

    @Override
    public UserResponse getUserByName(String name) {
        User user = userRepository.findByName(name)
        .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toUserResponse(user);
    }   
    
    @Override
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toUserResponse(user);
    }
    
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserResponse editUser(UserRequest userRequest) {
        User user = getCurrentUser();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    @Override 
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return currentUser;
    }
}
