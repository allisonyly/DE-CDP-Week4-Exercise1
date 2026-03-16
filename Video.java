public abstract class Video {
    private String title;
    private String genre;
    private boolean available;

    public Video(String title, String genre) {
        this.title = title;
        this.genre = genre;
        this.available = true;
    }

    public abstract void play();

    public void rentVideo() {
        this.available = false;
    }

    public void returnVideo() {
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isAvailable() {
        return available;
    }
}