package com.example.demo.services.s3;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3Object;
import com.example.demo.models.FileMeta;

public interface MetadataService {
    public void upload(MultipartFile file) throws IOException;
    public S3Object download(Long id);
    public List<FileMeta> list();
}

