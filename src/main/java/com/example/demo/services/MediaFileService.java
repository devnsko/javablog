package com.example.demo.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.MediaFile;
import com.example.demo.repository.MediaFileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MediaFileService {

    @Autowired
    private MediaFileRepository mediaFileRepository;

    public MediaFile saveFile(MultipartFile file) throws IOException {
        MediaFile mediaFile = new MediaFile();
        mediaFile.setName(file.getOriginalFilename());
        mediaFile.setType(file.getContentType());
        mediaFile.setData(file.getBytes());
        return mediaFileRepository.save(mediaFile);
    }

    public MediaFile getFile(Long id) {
        return mediaFileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found!"));
    }
}
