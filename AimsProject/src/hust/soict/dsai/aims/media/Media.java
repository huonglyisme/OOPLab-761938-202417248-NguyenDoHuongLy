package hust.soict.dsai.aims.media;

import java.util.Comparator;
import java.util.Objects;

public abstract class Media implements Comparable<Media> {
    private int id;
    private String title;
    private String category;
    private float cost;

    public static final Comparator<Media> COMPARE_BY_TITLE_COST =
            new MediaComparatorByTitleCost();

    public static final Comparator<Media> COMPARE_BY_COST_TITLE =
            new MediaComparatorByCostTitle();

    public Media() {
    }

    public Media(int id, String title, String category, float cost) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Media)) {
            return false;
        }

        Media other = (Media) obj;

        return Objects.equals(this.title, other.title)
                && Float.compare(this.cost, other.cost) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, cost);
    }

    @Override
    public int compareTo(Media other) {
        if (other == null) {
            throw new NullPointerException("Cannot compare Media with null");
        }

        String thisTitle = this.title == null ? "" : this.title;
        String otherTitle = other.title == null ? "" : other.title;

        int titleCompare = thisTitle.compareToIgnoreCase(otherTitle);

        if (titleCompare != 0) {
            return titleCompare;
        }

        return Float.compare(this.cost, other.cost);
    }
}