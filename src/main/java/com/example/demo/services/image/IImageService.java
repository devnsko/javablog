package com.example.demo.services.image;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ImageDto;
import com.example.demo.models.Image;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDto> saveImages(List<MultipartFile> files, Long postId);
    void updateImage(MultipartFile file, Long imageId);
}
