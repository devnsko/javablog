package com.example.demo.services.like;

import com.example.demo.models.Like;

public interface LikeService {
    boolean hasLike(Long blogId, Long userId);
    Like addLike(Long blogId, Long userId);
    void removeLike(Long blogId, Long userId);
    int likesCount(Long blogId);

}
