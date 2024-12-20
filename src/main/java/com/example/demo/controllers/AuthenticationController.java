package com.example.demo.controllers;

import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.user.LoginResponse;
import com.example.demo.dto.user.LoginUserDto;
import com.example.demo.dto.user.RegisterUserDto;
import com.example.demo.dto.user.UserResponse;
import com.example.demo.models.User;
import com.example.demo.services.JwtService;
import com.example.demo.services.auth.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(@RequestBody RegisterUserDto input) {
        UserResponse registeredUser = authenticationService.signup(input);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto input) {
        User authenticatedUser = authenticationService.authenticate(input);


        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = LoginResponse.builder()
                                        .token(jwtToken)
                                        .expiresIn(jwtService.getExpirationTime())
                                        .id(authenticatedUser.getId())
                                        .name(authenticatedUser.getName())
                                        .roles(authenticatedUser.getAuthorities().stream()
                                                .map(GrantedAuthority::getAuthority)
                                                .collect(Collectors.toList()))
                                        .type("Bearer")
                                        .build();
        return ResponseEntity.ok(loginResponse);
    }
}
