package hust.soict.dsai.aims.store;

import hust.soict.dsai.aims.media.Media;

import java.util.ArrayList;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<>();

    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }

    public void addMedia(Media media) {
        if (media == null) return;

        if (itemsInStore.contains(media)) {
            System.out.println("The media is already in the store.");
            return;
        }

        itemsInStore.add(media);
        System.out.println("The media has been added to the store.");
    }

    public void removeMedia(Media media) {
        if (media == null) return;

        if (itemsInStore.remove(media)) {
            System.out.println("The media has been removed from the store.");
        } else {
            System.out.println("The media was not found in the store.");
        }
    }

    public Media findMediaByTitle(String title) {
        if (title == null) return null;

        for (Media media : itemsInStore) {
            if (media.getTitle() != null
                    && media.getTitle().equalsIgnoreCase(title.trim())) {
                return media;
            }
        }
        return null;
    }

    public void printStore() {
        System.out.println("***********************STORE***********************");
        System.out.println("Items in store:");

        for (int i = 0; i < itemsInStore.size(); i++) {
            System.out.println((i + 1) + ". " + itemsInStore.get(i));
        }

        System.out.println("***************************************************");
    }
}