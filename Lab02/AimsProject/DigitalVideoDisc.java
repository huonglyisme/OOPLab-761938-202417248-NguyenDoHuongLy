public class DigitalVideoDisc {
    private int id;
    private String title;
    private String category;
    private String director;
    private int length;
    private float cost;

    private static int nbDigitalVideoDiscs = 0;

    private void assignId() {
        nbDigitalVideoDiscs++;
        this.id = nbDigitalVideoDiscs;
    }

    // Constructor 1: by title
    public DigitalVideoDisc(String title) {
        assignId();
        this.title = title;
    }

    // Constructor 2: by category, title and cost
    public DigitalVideoDisc(String category, String title, float cost) {
        assignId();
        this.category = category;
        this.title = title;
        this.cost = cost;
    }

    // Constructor 3: by director, category, title and cost
    public DigitalVideoDisc(String director, String category, String title, float cost) {
        assignId();
        this.director = director;
        this.category = category;
        this.title = title;
        this.cost = cost;
    }

    // Constructor 4: by all attributes
    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        assignId();
        this.title = title;
        this.category = category;
        this.director = director;
        this.length = length;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }

    public float getCost() {
        return cost;
    }

    public static int getNbDigitalVideoDiscs() {
        return nbDigitalVideoDiscs;
    }

    // Temporarily added for section 15
    public void setTitle(String title) {
        this.title = title;
    }
}