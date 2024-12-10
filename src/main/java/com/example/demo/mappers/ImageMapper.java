package com.example.demo.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.example.demo.dto.image.ImageResponse;
import com.example.demo.models.Image;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ImageMapper {

    ImageResponse toImageResponse(Image image);

    List<ImageResponse> toImageResponseList(List<Image> images);
}
