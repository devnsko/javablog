package com.example.demo.services.s3;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.example.demo.repository.FileMetaRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class AmazonS3ServiceImpl implements AmazonS3Service {
    
    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private FileMetaRepository fileMetaRepository;

    @Override
    public PutObjectResult upload(
        String path, 
        String fileName,
        Optional<Map<String, String>> optionalMetaData,
        InputStream inputStream) {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            optionalMetaData.ifPresent(map -> {
                if(!map.isEmpty()) {
                    map.forEach(objectMetadata::addUserMetadata);
                }
        });
        log.debug("Path: " + path + ", Name: " + fileName);
        return amazonS3.putObject(path, fileName, inputStream, objectMetadata);
    }

    public S3Object download(String path, String fileName) {
        return amazonS3.getObject(path, fileName);
    }
}
