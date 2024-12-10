package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long>{
    
}
