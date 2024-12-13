package com.example.demo.dto.blog;

import java.util.List;

import com.example.demo.dto.like.LikeResponse;

import lombok.Builder;

@Builder
public record BlogResponse(
    Long id,
    String title,
    String content,
    Long author_id,
    String author_name,
    List<LikeResponse> likes,
    String createdAt,
    String updatedAt
    ) { }