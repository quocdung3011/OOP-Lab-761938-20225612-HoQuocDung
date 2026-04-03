package aims;

public class DigitalVideoDisc {
    private String title;
    private String category;
    private String director;
    private int length;
    private float cost;
    private int id;
    private static int nbDigitalVideoDiscs = 0;

    public DigitalVideoDisc(String title) {
        this.title = title;
        nbDigitalVideoDiscs++;
        this.id = nbDigitalVideoDiscs;
    }

    public DigitalVideoDisc(String category, String title, float cost) {
        this(title);
        this.category = category;
        this.cost = cost;
    }

    public DigitalVideoDisc(String director, String category, String title, int length, float cost) {
        this(category, title, cost);
        this.director = director;
        this.length = length;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }
    public int getLength() { return length; }
    public void setLength(int length) { this.length = length; }
    public float getCost() { return cost; }
    public void setCost(float cost) { this.cost = cost; }
    public int getId() { return id; }
    public static int getNbDigitalVideoDiscs() { return nbDigitalVideoDiscs; }
}