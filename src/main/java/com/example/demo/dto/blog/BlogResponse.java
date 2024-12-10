package com.example.demo.dto.blog;

import lombok.Builder;

@Builder
public record BlogResponse(
    Long id,
    String title,
    String content,
    Long author_id,
    String author_name
    ) { }