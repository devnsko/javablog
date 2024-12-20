package com.example.demo.services.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.user.LoginUserDto;
import com.example.demo.dto.user.RegisterUserDto;
import com.example.demo.dto.user.UserResponse;
import com.example.demo.mappers.UserMapper;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public UserResponse signup(RegisterUserDto input) {
        User user = User.builder()
            .name(input.getName())
            .email(input.getEmail())
            .password(passwordEncoder.encode(input.getPassword()))
            .roles("ROLE_USER")
            .build();

        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                input.getName(),
                input.getPassword()
        ));

        User user = userRepository.findByName(input.getName()).orElseThrow();
        return user;
    }
}
