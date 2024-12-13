package com.example.demo.mappers;

import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.example.demo.dto.blog.BlogResponse;
import com.example.demo.dto.like.LikeResponse;
import com.example.demo.models.Blog;
import com.example.demo.models.Like;

@Mapper(componentModel = "spring")
public interface BlogMapper {
    
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "title", target = "title"),
        @Mapping(source = "content", target = "content"),
        @Mapping(source = "author.name", target = "author_name"),
        @Mapping(source = "author.id", target = "author_id"),
        @Mapping(source = "createdAt", target = "createdAt", dateFormat="yyyy-MM-dd HH:mm:ss"),
        @Mapping(source = "updatedAt", target = "updatedAt", dateFormat="yyyy-MM-dd HH:mm:ss")
    })
    BlogResponse toBlogResponse(Blog blog);

    
    @Mappings({

        @Mapping(source = "id", target = "id"),
        @Mapping(source = "blog.id", target = "blogId"),
        @Mapping(source = "user.id", target = "userId"),
        @Mapping(source = "user.name", target = "userName")
    })
    LikeResponse toLikeResponse(Like like);

    List<BlogResponse> toBlogResponses(List<Blog> blogs);

    default Optional<Blog> toOptionalEntity(Blog blog) {
        return Optional.ofNullable(blog);
    }
}