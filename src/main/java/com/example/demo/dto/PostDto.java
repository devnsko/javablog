package com.example.demo.dto;

import java.util.List;
import java.util.Locale.Category;

import lombok.Data;

@Data
public class PostDto {
    private Long id;
    private String author;
    private String title;
    private String description;
    private Category category;
    private List<ImageDto> images;

}
