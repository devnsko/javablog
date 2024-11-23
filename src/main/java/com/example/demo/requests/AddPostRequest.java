package com.example.demo.requests;

import com.example.demo.models.Category;

import lombok.Data;

@Data
public class AddPostRequest {
    private Long id;
    private String author;
    private String title;
    private String description;
    private Category category;
}
