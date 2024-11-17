package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
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

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.Post;
import com.example.demo.requests.AddPostRequest;
import com.example.demo.requests.UpdatePostRequest;
import com.example.demo.services.post.IPostService;

import lombok.RequiredArgsConstructor;
import response.ApiResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/posts")
public class PostController {
    private final IPostService postService;

    @GetMapping
    public ResponseEntity<ApiResponse> getPostsByCategoryAndAuthor(@RequestParam(required = false) String category, @RequestParam(required = false) String author) {
        try {
            List<Post> posts;
            if (author != null) {
                if(category != null) {
                    posts = postService.getPostsByCategoryAndAuthor(category, author);
                } else {
                    posts = postService.getPostsByAuthor(author);
                }
            } else if (category != null) {
                posts = postService.getPostsByCategory(category);
            } else {
                posts = postService.getAllPosts();
            }

            if (posts.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No posts found", null));
            }
            return ResponseEntity.ok(new ApiResponse("success", posts));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getPostById(@PathVariable Long id) {
        try {
            Post post = postService.getPostById(id);
            return ResponseEntity.ok(new ApiResponse("success", post));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addPost(@RequestBody AddPostRequest request) {
        try {
            Post post = postService.addPost(request);
            return ResponseEntity.ok(new ApiResponse("success", post));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updatePost(@PathVariable Long id, @RequestBody UpdatePostRequest request) {
        try {
            Post post = postService.updatePostById(request, id);
            return ResponseEntity.ok(new ApiResponse("success", post));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Long id) {
        try {
            postService.deletePostById(id);
            return ResponseEntity.ok(new ApiResponse("success", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
