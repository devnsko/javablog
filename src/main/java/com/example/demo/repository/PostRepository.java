package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCategoryName(String category);
    List<Post> findByAuthor(String author);
    List<Post> findByCategoryNameAndAuthor(String category, String author);
    Long countByCategoryName(String category);
}
