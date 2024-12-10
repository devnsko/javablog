package com.example.demo.services.post;

import java.util.List;

// import com.example.demo.dto.PostDto;
import com.example.demo.models.Post;
import com.example.demo.requests.AddPostRequest;
import com.example.demo.requests.UpdatePostRequest;

public interface IPostService {
    Post addPost(AddPostRequest request);
    Post getPostById(Long Id);
    void deletePostById(Long Id);
    Post updatePostById(UpdatePostRequest request, Long postId);
    
    List<Post> getAllPosts();
    List<Post> getPostsByCategory(String category);
    List<Post> getPostsByAuthor(String author);
    List<Post> getPostsByCategoryAndAuthor(String category, String author);
    // List<PostDto> getConvertedPosts(List<Post> posts);
    // PostDto convertToDto(Post post);
    Long countPostsByCategory(String category);
}
