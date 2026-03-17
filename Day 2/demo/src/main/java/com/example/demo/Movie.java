package com.example.demo;

public class Movie extends Video {
    public Movie(String title, String genre) {
        super(title, genre);
    }

    @Override
    public void play() {
        System.out.println("Playing movie: " + getTitle());
    }

    public void play(String quality) {
        System.out.println("Playing movie in " + quality + " quality.");
    }
}
