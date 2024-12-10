package com.example.demo.services.blog;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.blog.BlogResponse;
import com.example.demo.mappers.BlogMapper;
import com.example.demo.mappers.UserMapper;
import com.example.demo.models.Blog;
import com.example.demo.repository.BlogRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService{
    private final BlogRepository blogRepository;
    private final BlogMapper blogMapper;
    private final UserMapper userMapper;

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
    public BlogResponse getById(Long id) {
        Blog blog = blogRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Blog not found with ID:" + id));
        return blogMapper.toBlogResponse(blog);
    }

    @Override
    public BlogResponse create(Blog blog) {
        Blog savedBlog = blogRepository.save(blog);
        return blogMapper.toBlogResponse(savedBlog);
    }

    @Override
    public BlogResponse update(Blog blog, Long id) {
        Optional<Blog> existingBlog = blogRepository.findById(id);
        if (existingBlog.isPresent()) {
            Blog updatedBlog = Blog.builder()
                    .id(existingBlog.get().getId())
                    .title(blog.getTitle())
                    .content(blog.getContent())
                    .author(blog.getAuthor())
                    .build();
            Blog editedBlog = blogRepository.save(updatedBlog);
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
