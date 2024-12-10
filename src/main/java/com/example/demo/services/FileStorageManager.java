package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.SneakyThrows;

@Service
public class FileStorageManager {
    
    @SneakyThrows
    @Async
    public void save(MultipartFile file) {
        Thread.sleep(new Random().nextLong(3000, 6000));
        System.out.println(file.getOriginalFilename() + " uploaded at time: " + LocalDateTime.now());
    }
}
