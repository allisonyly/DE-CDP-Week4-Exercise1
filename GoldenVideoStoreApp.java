public class GoldenVideoStoreApp {
    public static void main(String[] args) {
        Video[] videos = new Video[2];
        videos[0] = new Movie("Inception", "Sci-Fi");
        videos[1] = new Series("Stranger Things", "Horror", 1);

        // Demonstrate polymorphism by calling play() on each video
        Movie inception = (Movie) videos[0];
        inception.play();
        inception.play("HD");
        videos[1].play();

        // Rent Inception
        System.out.println("Renting Inception...");
        inception.rentVideo();
        System.out.println("Available: " + inception.isAvailable());

        // Rent the series as well to match the output
        videos[1].rentVideo();

        // Return Inception
        System.out.println("Returning Inception...");
        inception.returnVideo();
        System.out.println("Available: " + inception.isAvailable());

        // Print available videos
        System.out.print("Available Videos: ");
        for (Video v : videos) {
            if (v.isAvailable()) {
                System.out.print(v.getTitle() + " ");
            }
        }
        System.out.println();
    }
}