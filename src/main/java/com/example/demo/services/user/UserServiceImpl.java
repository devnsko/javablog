package com.example.demo.services.user;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.config.ModifiedUserDetails;
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
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public String getCurrentUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof ModifiedUserDetails) {
            return ((ModifiedUserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    @Override
    public Optional<ModifiedUserDetails> getCurrentUserDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof ModifiedUserDetails) {
            return  Optional.of(((ModifiedUserDetails) principal));
        } else return Optional.empty();
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User user = User.builder()
            .name(userRequest.getName())
            .email(userRequest.getEmail())
            .password(passwordEncoder.encode(userRequest.getPassword()))
            .roles("ROLE_USER")
            .build();
        User savedUser = userRepository.save(user);
        UserResponse userResponse = userMapper.toUserResponse(savedUser);
        return userResponse;
    }

    @Override
    public UserResponse getUser(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No user found with id: " + id));
        UserResponse userResponse = userMapper.toUserResponse(user);
        return userResponse;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
