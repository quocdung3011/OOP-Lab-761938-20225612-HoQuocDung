package hust.soict.dsai.aims.media;

public abstract class Disc extends Media {
    private int length;
    private String director;

    public Disc() {}

    public Disc(String title, String category, 
                String director, int length, float cost) {
        super(title, category, cost);
        this.director = director;
        this.length = length;
    }

    public int getLength() { return length; }
    public String getDirector() { return director; }
    public void setLength(int length) { this.length = length; }
    public void setDirector(String director) { this.director = director; }
    
}