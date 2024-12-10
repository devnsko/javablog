package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.user.UserRequest;
import com.example.demo.dto.user.UserResponse;
import com.example.demo.models.User;
import com.example.demo.services.user.UserService;

import lombok.RequiredArgsConstructor;
import response.ApiResponse;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("auth/register")
    public String register(Model model) {
        model.addAttribute("user", new UserRequest());
        return "auth/register";
    }

    @PostMapping("auth/register")
    public String addUser(@ModelAttribute UserRequest user) {
        userService.createUser(user);
        return "redirect:/login";

    }

    @ResponseBody
    @GetMapping("${api.prefix}/users/{id}")
    public ResponseEntity<ApiResponse> getUser(@PathVariable Long id) {
        UserResponse user = userService.getUser(id);
        return ResponseEntity.ok(new ApiResponse("success", user));
    }

    @ResponseBody
    @DeleteMapping("${api.prefix}/users/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponse("success", null));
    }
}
