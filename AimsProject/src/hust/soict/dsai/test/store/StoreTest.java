package hust.soict.dsai.test.store;

import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.media.*; 


public class StoreTest {
    public static void main(String[] args) {
        Store store = new Store();

        DigitalVideoDisc dvd = new DigitalVideoDisc(
                "The Lion King",
                "Animation",
                "Roger Allers",
                87,
                19.95f
        );

        Book book = new Book(1, "Clean Code", "Programming", 25.5f);

        CompactDisc cd = new CompactDisc("Greatest Hits", "Music", 15.5f, "Artist A");
        cd.addTrack(new Track("Track 1", 4));
        cd.addTrack(new Track("Track 2", 5));

        store.addMedia(dvd);
        store.addMedia(book);
        store.addMedia(cd);

        System.out.println();
        store.printStore();

        System.out.println("\nFind by title = Clean Code");
        System.out.println(store.findMediaByTitle("Clean Code"));

        System.out.println("\nRemove DVD");
        store.removeMedia(dvd);

        System.out.println();
        store.printStore();
    }
}