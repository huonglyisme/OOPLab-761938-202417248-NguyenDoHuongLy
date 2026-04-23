package hust.soict.dsai.test.media;

import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Track;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MediaTest {
    public static void main(String[] args) {
        List<Media> mediae = new ArrayList<>();

        Book book = new Book(1, "Clean Code", "Programming", 25.5f);

        DigitalVideoDisc dvd = new DigitalVideoDisc(
                "The Lion King",
                "Animation",
                "Roger Allers",
                87,
                19.95f
        );

        CompactDisc cd = new CompactDisc("Greatest Hits", "Music", 15.5f, "Artist A");
        cd.addTrack(new Track("Track 1", 4));
        cd.addTrack(new Track("Track 2", 5));

        mediae.add(cd);
        mediae.add(dvd);
        mediae.add(book);

        System.out.println("=== Original list ===");
        for (Media m : mediae) {
            System.out.println(m.toString());
        }

        Collections.sort(mediae, Media.COMPARE_BY_TITLE_COST);
        System.out.println("\n=== Sort by title then cost ===");
        for (Media m : mediae) {
            System.out.println(m);
        }

        Collections.sort(mediae, Media.COMPARE_BY_COST_TITLE);
        System.out.println("\n=== Sort by cost then title ===");
        for (Media m : mediae) {
            System.out.println(m);
        }
    }
}
