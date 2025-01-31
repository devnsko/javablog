package com.example.demo.requests;

import java.util.List;

import com.example.demo.models.Category;
import com.example.demo.models.Image;

import lombok.Data;

@Data
public class UpdatePostRequest {
    private Long id;
    private String author;
    private String title;
    private String description;
    private Category category;
    private List<Image> images;
}
