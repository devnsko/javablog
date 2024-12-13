package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long>{
    List<Blog> findAllByOrderByIdDesc(Pageable pageable);
    List<Blog> findAllByAuthorNameOrderByIdDesc(String author);
    
}
