package com.example.demo.services.like;

import com.example.demo.dto.like.LikeResponse;

public interface LikeService {
    boolean hasLike(Long blogId, Long userId);
    LikeResponse addLike(Long blogId, Long userId);
    void removeLike(Long blogId, Long userId);
    int likesCount(Long blogId);

}
