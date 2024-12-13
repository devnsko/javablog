package com.example.demo.services.blog;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.config.ModifiedUserDetails;
import com.example.demo.dto.blog.BlogRequest;
import com.example.demo.dto.blog.BlogResponse;
import com.example.demo.dto.user.UserResponse;
import com.example.demo.mappers.BlogMapper;
import com.example.demo.models.Blog;
import com.example.demo.models.User;
import com.example.demo.repository.BlogRepository;
import com.example.demo.services.user.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService{
    private final BlogRepository blogRepository;
    private final BlogMapper blogMapper;
    private final UserService userService;

    @Override
    public List<BlogResponse> getAll() {
        List<Blog> blogs = blogRepository.findAll();
        List<BlogResponse> blogResponses = blogMapper.toBlogResponses(blogs);
        return blogResponses;
    }

    @Override
    public List<BlogResponse> getAllOrderByDescPageable(int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        List<Blog> blogs = blogRepository.findAllByOrderByIdDesc(pageable);
        List<BlogResponse> blogResponses = blogMapper.toBlogResponses(blogs);
        return blogResponses;
    }

    @Override
    public List<BlogResponse> getByAuthorOrderByDesc(String author) {
        List<Blog> blogs = blogRepository.findAllByAuthorNameOrderByIdDesc(author);
        List<BlogResponse> blogResponses = blogMapper.toBlogResponses(blogs);
        return blogResponses;
    }

    @Override
    public BlogResponse getById(Long id) {
        Blog blog = blogRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Blog not found with ID:" + id));
        return blogMapper.toBlogResponse(blog);
    }

    @Override
    public BlogResponse create(BlogRequest blogRequest) {
        Optional<ModifiedUserDetails> userOptional = userService.getCurrentUserDetails();
        ModifiedUserDetails userDetails = userOptional.orElseThrow(() -> new RuntimeException("Issues with user"));
        User user = userDetails.getUser();
        Blog blog = Blog.builder()
                        .title(blogRequest.getTitle())
                        .content(blogRequest.getContent())
                        .author(user)
                        .build();
        Blog savedBlog = blogRepository.save(blog);
        return blogMapper.toBlogResponse(savedBlog);
    }

    @Override
    public BlogResponse update(BlogRequest blogRequest, Long id) {
        Optional<Blog> existingBlog = blogRepository.findById(id);
        if (existingBlog.isPresent()) {
            Blog blog = existingBlog.get();
            blog.setTitle(blogRequest.getTitle());
            blog.setContent(blogRequest.getContent());
            Blog editedBlog = blogRepository.save(blog);
            return blogMapper.toBlogResponse(editedBlog);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        blogRepository.deleteById(id);
    }
}
