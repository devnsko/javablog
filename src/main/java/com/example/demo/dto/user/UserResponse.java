package com.example.demo.dto.user;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    
    private Long id;
    private String name;
    private String email;
    private String roles;
    private String createdAt;
}
