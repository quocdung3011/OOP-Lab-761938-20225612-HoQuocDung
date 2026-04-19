package hust.soict.dsai.aims.media;

public abstract class Media {
    private int id;
    private String title;
    private String category;
    private float cost;

    public Media() {}

    public Media(String title, String category, float cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public float getCost() { return cost; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setCategory(String category) { this.category = category; }
    public void setCost(float cost) { this.cost = cost; }

    public boolean isMatch(String title) {
        String[] keywords = title.split(" ");
        for (String keyword : keywords) {
            if (this.title.toLowerCase()
                    .contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public abstract String toString();
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Media)) return false;
        Media media = (Media) o;
        return this.getTitle().equals(media.getTitle());
    }
}