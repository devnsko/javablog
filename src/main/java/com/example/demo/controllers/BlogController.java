package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.blog.BlogRequest;
import com.example.demo.dto.blog.BlogResponse;
import com.example.demo.services.blog.BlogService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("posts")
public class BlogController {
    private final BlogService blogService;

    @GetMapping
    public ResponseEntity<List<BlogResponse>> lastPosts(@RequestParam(defaultValue = "1") int page) {
        if (page <= 0) { page = 1; }
        page = page - 1;
        List<BlogResponse> blogResponses = blogService.getAllOrderByDescPageable(page,6);
        return ResponseEntity.ok(blogResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> postById(@PathVariable Long id) {
        BlogResponse blogResponse = blogService.getById(id);
        return ResponseEntity.ok(blogResponse);
    }

    @PostMapping
    public ResponseEntity<BlogResponse> createPost(BlogRequest blogRequest) {
        BlogResponse blogResponse = blogService.create(blogRequest);
        return ResponseEntity.ok(blogResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogResponse> editById(@PathVariable Long id, @RequestBody BlogRequest blogRequest) {
        BlogResponse editedBlog = blogService.update(blogRequest, id);
        return ResponseEntity.ok(editedBlog);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteById(@PathVariable Long id) {
        blogService.delete(id);
        return ResponseEntity.ok("deleted");
    }
}
