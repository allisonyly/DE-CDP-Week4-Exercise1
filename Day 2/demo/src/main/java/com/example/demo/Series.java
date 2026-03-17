package com.example.demo;

public class Series extends Video {
    private int episodeNum;

    public Series(String title, String genre, int episodeNum) {
        super(title, genre);
        this.episodeNum = episodeNum;
    }

    @Override
    public void play() {
        System.out.println("Playing series: " + getTitle());
    }

    public int getEpisodeNum() {
        return episodeNum;
    }

    public void setEpisodeNum(int episodeNum) {
        this.episodeNum = episodeNum;
    }
}
