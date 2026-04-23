package hust.soict.dsai.aims.cart;

import hust.soict.dsai.aims.media.Media;

import java.util.ArrayList;
import java.util.Collections;

public class Cart {
    private ArrayList<Media> itemsOrdered = new ArrayList<>();

    public ArrayList<Media> getItemsOrdered() {
        return itemsOrdered;
    }

    public void addMedia(Media media) {
        if (media == null) return;

        if (itemsOrdered.contains(media)) {
            System.out.println("The media is already in the cart.");
            return;
        }

        itemsOrdered.add(media);
        System.out.println("The media has been added.");
    }

    public void removeMedia(Media media) {
        if (media == null) return;

        if (itemsOrdered.remove(media)) {
            System.out.println("The media has been removed.");
        } else {
            System.out.println("The media was not found.");
        }
    }

    public float totalCost() {
        float total = 0;
        for (Media media : itemsOrdered) {
            total += media.getCost();
        }
        return total;
    }

    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");

        for (int i = 0; i < itemsOrdered.size(); i++) {
            System.out.println((i + 1) + ". " + itemsOrdered.get(i));
        }

        System.out.println("Total cost: " + totalCost() + " $");
        System.out.println("***************************************************");
    }

    public void searchById(int id) {
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                System.out.println("Result:");
                System.out.println(media);
                return;
            }
        }
        System.out.println("No matching media found.");
    }

    public void searchByTitle(String title) {
        if (title == null) {
            System.out.println("No matching media found.");
            return;
        }

        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.getTitle() != null
                    && media.getTitle().equalsIgnoreCase(title.trim())) {
                if (!found) {
                    System.out.println("Result:");
                }
                System.out.println(media);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching media found.");
        }
    }

    public void sortByTitleCost() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
    }

    public void sortByCostTitle() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
    }

    public void placeOrder() {
        if (itemsOrdered.isEmpty()) {
            System.out.println("The cart is empty.");
            return;
        }

        System.out.println("An order has been created.");
        itemsOrdered.clear();
    }
}