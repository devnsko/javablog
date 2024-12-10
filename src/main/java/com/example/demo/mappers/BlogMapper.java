package com.example.demo.mappers;

import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.example.demo.dto.blog.BlogResponse;
import com.example.demo.models.Blog;

@Mapper(componentModel = "spring")
public interface BlogMapper {
    
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "title", target = "title"),
        @Mapping(source = "content", target = "content"),
        @Mapping(source = "author.name", target = "author_name"),
        @Mapping(source = "author.id", target = "author_id")
    })
    BlogResponse toBlogResponse(Blog blog);

    List<BlogResponse> toBlogResponses(List<Blog> blogs);

    default Optional<Blog> toOptionalEntity(Blog blog) {
        return Optional.ofNullable(blog);
    }
}