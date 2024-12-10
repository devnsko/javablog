package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.FileMeta;

@Repository
public interface FileMetaRepository extends CrudRepository<FileMeta, Long> {
    
}
