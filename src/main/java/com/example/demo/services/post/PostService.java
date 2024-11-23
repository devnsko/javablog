package com.example.demo.services.post;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ImageDto;
import com.example.demo.dto.PostDto;
import com.example.demo.exceptions.PostNotFoundException;
import com.example.demo.models.Category;
import com.example.demo.models.Image;
import com.example.demo.models.Post;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.requests.AddPostRequest;
import com.example.demo.requests.UpdatePostRequest;

import java.util.Optional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {
    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final ImageRepository imageRepository;
    @Autowired
    private final ModelMapper modelMapper;
    
    @Override
    public Post addPost(AddPostRequest request) {
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() -> {
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
        request.setCategory(category);
        return postRepository.save(createPost(request, category));
    }
    
    private Post createPost(AddPostRequest request, Category category) {
        return new Post(
            request.getTitle(),
            request.getDescription(),
            request.getAuthor(),
            request.getCategory()
        );
    }
    
    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post not found!"));
    }
    
    @Override
    public void deletePostById(Long id) {
        postRepository.findById(id).ifPresentOrElse(postRepository::delete, () -> {throw new PostNotFoundException("Post not found!");});
    }
    
    @Override
    public Post updatePostById(UpdatePostRequest request, Long postId) {
        return postRepository.findById(postId)
            .map(existingPost -> updateExistingPost(existingPost, request))
            .map(postRepository :: save)
            .orElseThrow(() -> new PostNotFoundException("Post not found"));
    }
    
    private Post updateExistingPost(Post existingPost, UpdatePostRequest request) {
        existingPost.setTitle(request.getTitle());
        existingPost.setDescription(request.getDescription());
        existingPost.setAuthor(request.getAuthor());
        
        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingPost.setCategory(category);
        return existingPost;
    }
    
    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
    
    @Override
    public List<Post> getPostsByCategory(String category) {
        return postRepository.findByCategoryName(category);
    }
    
    @Override
    public List<Post> getPostsByAuthor(String author) {
        return postRepository.findByAuthor(author);
    }
    
    @Override
    public List<Post> getPostsByCategoryAndAuthor(String category, String author) {
        return postRepository.findByCategoryNameAndAuthor(category, author);
    }
    
    @Override
    public Long countPostsByCategory(String category) {
        return postRepository.countByCategoryName(category);
    }
    
    @Override
    public List<PostDto> getConvertedPosts(List<Post> posts) {
        return posts.stream().map(this::convertToDto).toList();
    }
    
    @Override
    public PostDto convertToDto(Post post) {
        PostDto postDto = modelMapper.map(post, PostDto.class);
        List<Image> images = imageRepository.findByPostId(post.getId());
        List<ImageDto> imageDtos = images.stream().map(image -> modelMapper.map(image, ImageDto.class)).toList();
        postDto.setImages(imageDtos);
        return postDto;
    }
}
