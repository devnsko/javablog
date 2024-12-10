package com.example.demo.dto.post;

import java.util.List;

import com.example.demo.dto.category.CategoryResponse;
import com.example.demo.dto.image.ImageResponse;

public record PostResponse(
    Long id,
    String author,
    String title,
    String description,
    CategoryResponse category,
    List<ImageResponse> images
    ) {
    
}
