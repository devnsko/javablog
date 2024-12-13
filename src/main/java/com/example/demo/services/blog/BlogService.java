package com.example.demo.services.blog;

import java.util.List;

import com.example.demo.dto.blog.BlogRequest;
import com.example.demo.dto.blog.BlogResponse;

public interface BlogService {
    BlogResponse create(BlogRequest blogRequest);
    BlogResponse update(BlogRequest blogRequest, Long id);
    void delete(Long id);
    List<BlogResponse> getAll();
    List<BlogResponse> getAllOrderByDescPageable(int page, int count);
    List<BlogResponse> getByAuthorOrderByDesc(String author);
    BlogResponse getById(Long id);


}
