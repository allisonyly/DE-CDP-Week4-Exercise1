package com.example.demo.repository;

import com.example.demo.Video;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryVideoRepository implements VideoRepository {

    private final List<Video> videos = new ArrayList<>();

    public InMemoryVideoRepository() {
        // initial sample data
        videos.add(new com.example.demo.Movie("The Matrix", "Action"));
        videos.add(new com.example.demo.Series("The Office", "Comedy", 1));
    }

    @Override
    public List<Video> findAll() {
        return new ArrayList<>(videos);
    }

    @Override
    public List<Video> findAvailable() {
        List<Video> available = new ArrayList<>();
        for (Video v : videos) {
            if (v.isAvailable()) {
                available.add(v);
            }
        }
        return available;
    }

    @Override
    public Video save(Video video) {
        videos.add(video);
        return video;
    }

    @Override
    public Video findByTitle(String title) {
        if (title == null) {
            return null;
        }
        Optional<Video> found = videos.stream()
                .filter(v -> v.getTitle().equalsIgnoreCase(title))
                .findFirst();
        return found.orElse(null);
    }
}
