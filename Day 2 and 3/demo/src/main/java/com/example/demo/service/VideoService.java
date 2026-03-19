package com.example.demo.service;

import com.example.demo.Movie;
import com.example.demo.Series;
import com.example.demo.Video;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    private final com.example.demo.repository.VideoRepository videoRepository;

    public VideoService(com.example.demo.repository.VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    public List<Video> getAvailableVideos() {
        return videoRepository.findAvailable();
    }

    public Video addMovie(Movie movie) {
        return videoRepository.save(movie);
    }

    public Video addSeries(Series series) {
        return videoRepository.save(series);
    }

    public Video findVideoByTitle(String title) {
        return videoRepository.findByTitle(title);
    }

    public Video rentVideo(String title) {
        Video video = findVideoByTitle(title);
        if (video != null && video.isAvailable()) {
            video.rentVideo();
        }
        return video;
    }

    public Video returnVideo(String title) {
        Video video = findVideoByTitle(title);
        if (video != null && !video.isAvailable()) {
            video.returnVideo();
        }
        return video;
    }
}
