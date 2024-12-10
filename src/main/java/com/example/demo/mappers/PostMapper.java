package com.example.demo.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.example.demo.dto.post.PostResponse;
import com.example.demo.models.Post;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostMapper {
    
    PostResponse toPostResponse(Post post);

    List<PostResponse> toPostResponseList(List<Post> posts);
}
