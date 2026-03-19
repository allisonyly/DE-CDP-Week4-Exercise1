package com.example.demo.repository;

import com.example.demo.Video;

import java.util.List;

public interface VideoRepository {
    List<Video> findAll();
    List<Video> findAvailable();
    Video save(Video video);
    Video findByTitle(String title);
}
