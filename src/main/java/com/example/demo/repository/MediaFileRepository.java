package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.MediaFile;

@Repository
public interface MediaFileRepository extends JpaRepository<MediaFile, Long> {
}
