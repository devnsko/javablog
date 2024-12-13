package com.example.demo.services.like;

import org.springframework.stereotype.Service;

import com.example.demo.models.Blog;
import com.example.demo.models.Like;
import com.example.demo.models.User;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.LikeRepository;
import com.example.demo.repository.UserRepository;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;

    @Override
    public boolean hasLike(Long blogId, Long userId) {
        return likeRepository.existsByBlogIdAndUserId(blogId, userId);
    }

    @Override
    public Like addLike(Long blogId, Long userId) {
        if (hasLike(blogId, userId)) throw new RuntimeException("blog with Id: " + blogId + " already has like from user with Id: " + userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Blog blog = blogRepository.findById(userId).orElseThrow(() -> new RuntimeException("Blog not found"));
        
        Like like = new Like();
        like.setUser(user);
        like.setBlog(blog);
        Like savedLike = likeRepository.save(like);
        return savedLike;
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
