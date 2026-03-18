package com.example.demo.controller;

import com.example.demo.Movie;
import com.example.demo.Series;
import com.example.demo.Video;
import com.example.demo.service.VideoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    // Returns all videos in the in-memory store
    @GetMapping
    public List<Video> getAllVideos() {
        return videoService.getAllVideos();
    }

    // Adds a new Movie object supplied in the request body
    @PostMapping("/add/movie")
    public Video addMovie(@RequestBody Movie movie) {
        return videoService.addMovie(movie);
    }

    // Adds a new Series object supplied in the request body
    @PostMapping("/add/series")
    public Video addSeries(@RequestBody Series series) {
        return videoService.addSeries(series);
    }

    // Returns only videos marked as available
    @GetMapping("/available")
    public List<Video> getAvailableVideos() {
        return videoService.getAvailableVideos();
    }

    // Get a single video by title (case-insensitive)
    @GetMapping("/{title}")
    public ResponseEntity<Video> getVideoByTitle(@PathVariable String title) {
        Video video = videoService.findVideoByTitle(title);
        return video == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(video);
    }

    // Mark a video as rented (available = false)
    @PutMapping("/{title}/rent")
    public ResponseEntity<Video> rentVideo(@PathVariable String title) {
        Video video = videoService.rentVideo(title);
        return video == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(video);
    }

    // Mark a video as returned (available = true)
    @PutMapping("/{title}/return")
    public ResponseEntity<Video> returnVideo(@PathVariable String title) {
        Video video = videoService.returnVideo(title);
        return video == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(video);
    }
}

