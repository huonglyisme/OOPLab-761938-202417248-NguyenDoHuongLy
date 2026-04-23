package hust.soict.dsai.aims.media;

import java.util.ArrayList;

public class Book extends Media {
    private ArrayList<String> authors = new ArrayList<>();

    public Book() {
        super();
    }

    public Book(int id, String title, String category, float cost) {
        super(id, title, category, cost);
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void addAuthor(String authorName) {
        if (authorName == null || authorName.trim().isEmpty()) {
            return;
        }

        if (authors.contains(authorName)) {
            System.out.println("The author already exists.");
        } else {
            authors.add(authorName);
            System.out.println("The author has been added.");
        }
    }

    public void removeAuthor(String authorName) {
        if (authorName == null || authorName.trim().isEmpty()) {
            return;
        }

        if (authors.contains(authorName)) {
            authors.remove(authorName);
            System.out.println("The author has been removed.");
        } else {
            System.out.println("The author was not found.");
        }
    }

    @Override
    public String toString() {
        return "Book - " + getTitle() + " - " + getCategory() + " - " + getCost() + " $";
    }
}
