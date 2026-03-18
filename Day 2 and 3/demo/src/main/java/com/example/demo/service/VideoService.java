package com.example.demo.service;

import com.example.demo.Movie;
import com.example.demo.Series;
import com.example.demo.Video;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    private final List<Video> videos = new ArrayList<>();

    public VideoService() {
        // Sample data for in-memory demo store
        videos.add(new Movie("The Matrix", "Action"));
        videos.add(new Series("The Office", "Comedy", 1));
    }

    public List<Video> getAllVideos() {
        return new ArrayList<>(videos);
    }

    public List<Video> getAvailableVideos() {
        List<Video> available = new ArrayList<>();
        for (Video v : videos) {
            if (v.isAvailable()) {
                available.add(v);
            }
        }
        return available;
    }

    public Video addMovie(Movie movie) {
        videos.add(movie);
        return movie;
    }

    public Video addSeries(Series series) {
        videos.add(series);
        return series;
    }

    public Video findVideoByTitle(String title) {
        if (title == null) {
            return null;
        }
        Optional<Video> found = videos.stream()
                .filter(video -> video.getTitle().equalsIgnoreCase(title))
                .findFirst();
        return found.orElse(null);
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
