package com.example.demo.dto.like;

public record LikeResponse(
    Long id,
    Long blogId,
    Long userId,
    String userName
) {
    
}
