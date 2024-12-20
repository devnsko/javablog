package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.like.LikeResponse;
import com.example.demo.services.like.LikeService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequiredArgsConstructor
@RequestMapping("posts/like")
public class LikeController {
    private final LikeService likeService;

    @GetMapping("/{blogId}/count")
    public ResponseEntity<?> postLikesCount(@PathVariable Long blogId) {
        int count = likeService.likesCount(blogId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{blogId}/isLiked/{userId}")
    public ResponseEntity<?> IsLikedPost(@PathVariable Long blogId, @PathVariable Long userId) {
        boolean liked = likeService.hasLike(blogId, userId);
        return ResponseEntity.ok(liked);
    }
    
    @PostMapping("/{blogId}/{userId}")
    public ResponseEntity<LikeResponse> addLike(@PathVariable Long blogId, @PathVariable Long userId) {
        LikeResponse like = likeService.addLike(blogId, userId);
        return ResponseEntity.ok(like);
    }

    @DeleteMapping("/{blogId}/{userId}")
    public ResponseEntity<?> removeLike(@PathVariable Long blogId, @PathVariable Long userId) {
        likeService.removeLike(blogId, userId);
        return ResponseEntity.ok("success");
    }
    
}
