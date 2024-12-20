package com.example.demo.services.like;

import org.springframework.stereotype.Service;

import com.example.demo.dto.like.LikeResponse;
import com.example.demo.mappers.BlogMapper;
import com.example.demo.models.Blog;
import com.example.demo.models.Like;
import com.example.demo.models.User;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.LikeRepository;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;

    private final BlogMapper blogMapper;

    @Override
    public boolean hasLike(Long blogId, Long userId) {
        return likeRepository.existsByBlogIdAndUserId(blogId, userId);
    }

    @Override
    public LikeResponse addLike(Long blogId, Long userId) {
        if (hasLike(blogId, userId)) throw new RuntimeException("blog with Id: " + blogId + " already has like from user with Id: " + userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Blog blog = blogRepository.findById(blogId).orElseThrow(() -> new RuntimeException("Blog not found"));
        
        Like like = new Like();
        like.setBlog(blog);
        like.setUser(user);
        Like savedLike = likeRepository.save(like);
        return blogMapper.toLikeResponse(savedLike);
    }

    @Override
    public void removeLike(Long blogId, Long userId) {
        Like like = likeRepository.findByBlogIdAndUserId(blogId, userId).orElseThrow(() -> new RuntimeException("blog with Id: " + blogId + 
        " don't has like yet from user with Id: " + userId + ". Else blog or User doesn't exists"));
        likeRepository.delete(like);
    }

    @Override
    public int likesCount(Long blogId) {
        int counter = likeRepository.countByBlogId(blogId);
        return counter;
    }
    
}
