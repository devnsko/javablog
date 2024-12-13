package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByBlogIdAndUserId(Long blogId, Long userId);
    Optional<Like> findByBlogIdAndUserId(Long blogId, Long userId);
    int countByBlogId(Long blogId);
}
