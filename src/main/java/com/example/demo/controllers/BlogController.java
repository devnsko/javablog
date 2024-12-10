package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.config.ModifiedUserDetails;
import com.example.demo.dto.blog.BlogRequest;
import com.example.demo.dto.blog.BlogResponse;
import com.example.demo.dto.user.UserResponse;
import com.example.demo.models.Blog;
import com.example.demo.models.Post;
import com.example.demo.models.User;
import com.example.demo.services.blog.BlogService;
import com.example.demo.services.user.UserService;

import lombok.AllArgsConstructor;
import response.ApiResponse;

@AllArgsConstructor
@Controller
@RequestMapping
public class BlogController {
    private final BlogService blogService;
    private final UserService userService;

    @GetMapping
    public String home() {
        return "redirect:/blog";
    }

    @GetMapping("blog")
    public String getAll(Model model) {
        model.addAttribute("username", userService.getCurrentUserName());
        model.addAttribute("blogs", blogService.getAll());
        return "blog/list";
    }

    @GetMapping("blog/{id}")
    public String getById(@PathVariable Long id, Model model) {
        model.addAttribute("username", userService.getCurrentUserName());
        model.addAttribute("blog", blogService.getById(id));
        return "blog/post";
    }

    // @PostMapping("blog")
    // public String create(@ModelAttribute Blog blog) {
    //     blogService.create(blog);
    //     return "redirect:/blog";
    // }
    
    // Show the form to create a new post
    @GetMapping("blog/new")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String newPostForm(Model model) {
        model.addAttribute("username", userService.getCurrentUserName());
        model.addAttribute("blogRequest", new BlogRequest()); // Pass an empty post object for form binding
        return "blog/new"; // Render "new.html" from templates/blog
    }

    // Handle form submission for creating a new post
    @PostMapping("blog/new")
    public String createPost(@ModelAttribute BlogRequest blogRequest) {
        Optional<ModifiedUserDetails> userOptional = userService.getCurrentUserDetails();
        ModifiedUserDetails userDetails = userOptional.orElseThrow(() -> new RuntimeException("Issues with user"));
        User user = userDetails.getUser();
        Blog blog = Blog.builder()
                        .title(blogRequest.getTitle())
                        .content(blogRequest.getContent())
                        .author(user)
                        .build();
        blogService.create(blog);
        return "redirect:/blog"; // Redirect back to the list of posts
    }

    // @PutMapping("blog/{id}")
    // public ResponseEntity<ApiResponse> editById(@PathVariable Long id, @RequestBody Blog blog) {
    //     BlogResponse editedBlog = blogService.update(blog, id);
    //     return ResponseEntity.ok(new ApiResponse("success", editedBlog));
    // }

    @DeleteMapping("blog/{id}")
    public  ResponseEntity<ApiResponse> deleteById(@PathVariable Long id) {
        blogService.delete(id);
        return ResponseEntity.ok(new ApiResponse("success", null));
    }
    @GetMapping("blog/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        blogService.delete(id);
        return "redirect:/blog"; // Redirect back to the list of posts
    }
}
