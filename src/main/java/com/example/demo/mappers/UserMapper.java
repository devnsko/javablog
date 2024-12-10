package com.example.demo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.example.demo.dto.user.UserResponse;
import com.example.demo.models.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(source = "createdAt", target = "createdAt", dateFormat="yyyy-MM-dd HH:mm:ss")
    UserResponse toUserResponse(User user);
}
