package com.example.demo.services.blog;

import java.util.List;

import com.example.demo.dto.blog.BlogResponse;
import com.example.demo.models.Blog;

public interface BlogService {
    BlogResponse create(Blog blog);
    BlogResponse update(Blog blog, Long id);
    void delete(Long id);
    List<BlogResponse> getAll();
    BlogResponse getById(Long id);

}
