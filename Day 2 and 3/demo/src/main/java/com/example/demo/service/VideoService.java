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

    // In-memory store of Video objects (Movie + Series)
    private final List<Video> videos = new ArrayList<>();

    // Constructor loads initial sample data for demo purposes
    public VideoService() {
        videos.add(new Movie("The Matrix", "Action"));
        videos.add(new Series("The Office", "Comedy", 1));
    }

    // Return a copy of all videos so caller can't mutate our internal list directly
    public List<Video> getAllVideos() {
        return new ArrayList<>(videos);
    }

    // Return only those videos that are currently available (not rented)
    public List<Video> getAvailableVideos() {
        List<Video> available = new ArrayList<>();
        for (Video v : videos) {
            if (v.isAvailable()) {
                available.add(v);
            }
        }
        return available;
    }

    // Add a Movie object to the store
    public Video addMovie(Movie movie) {
        videos.add(movie);
        return movie;
    }

    // Add a Series object to the store
    public Video addSeries(Series series) {
        videos.add(series);
        return series;
    }

    // Find the first matching video by title (case-insensitive)
    public Video findVideoByTitle(String title) {
        if (title == null) {
            return null;
        }
        Optional<Video> found = videos.stream()
                .filter(video -> video.getTitle().equalsIgnoreCase(title))
                .findFirst();
        return found.orElse(null);
    }

    // Mark a video as rented if found and currently available
    public Video rentVideo(String title) {
        Video video = findVideoByTitle(title);
        if (video != null && video.isAvailable()) {
            video.rentVideo();
        }
        return video;
    }

    // Mark a video as returned if found and currently not available
    public Video returnVideo(String title) {
        Video video = findVideoByTitle(title);
        if (video != null && !video.isAvailable()) {
            video.returnVideo();
        }
        return video;
    }
}
