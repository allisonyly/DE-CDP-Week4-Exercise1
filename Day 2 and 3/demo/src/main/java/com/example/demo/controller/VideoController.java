package com.example.demo.controller;

import com.example.demo.Movie;
import com.example.demo.Series;
import com.example.demo.Video;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    // List of videos for the in-memory store (demo purposes)
    private final List<Video> videos = new ArrayList<>();

    // Sample data when controller is instantiated.
    public VideoController() {
        videos.add(new Movie("The Matrix", "Action"));
        videos.add(new Series("The Office", "Comedy", 1));
    }

    // Returns all videos in the in-memory store
    @GetMapping
    public List<Video> getAllVideos() {
        return new ArrayList<>(videos);
    }

    // Adds a new Movie object supplied in the request body
    @PostMapping("/add/movie")
    public Video addMovie(@RequestBody Movie movie) {
        videos.add(movie);
        return movie;
    }

    // Returns only videos marked as available
    @GetMapping("/available")
    public List<Video> getAvailableVideos() {
        List<Video> all = getAllVideos();
        List<Video> available = new ArrayList<>();
        for (Video v : all) {
            if (v.isAvailable()) {
                available.add(v);
            }
        }
        return available;
    }

    // Get a single video by title (case-insensitive)
    @GetMapping("/{title}")
    public ResponseEntity<Video> getVideoByTitle(@PathVariable String title) {
        Video video = findVideoByTitle(title);
        return video == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(video);
    }

    // Mark a video as rented (available = false)
    @PutMapping("/{title}/rent")
    public ResponseEntity<Video> rentVideo(@PathVariable String title) {
        Video video = findVideoByTitle(title);
        if (video == null) {
            return ResponseEntity.notFound().build();
        }
        video.rentVideo();
        return ResponseEntity.ok(video);
    }

    // Mark a video as returned (available = true)
    @PutMapping("/{title}/return")
    public ResponseEntity<Video> returnVideo(@PathVariable String title) {
        Video video = findVideoByTitle(title);
        if (video == null) {
            return ResponseEntity.notFound().build();
        }
        video.returnVideo();
        return ResponseEntity.ok(video);
    }

    // Helper used by endpoints to locate a video by title
    private Video findVideoByTitle(String title) {
        for (Video v : videos) {
            if (v.getTitle().equalsIgnoreCase(title)) {
                return v;
            }
        }
        return null;
    }
}
