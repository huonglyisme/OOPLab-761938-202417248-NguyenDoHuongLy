package hust.soict.dsai.test.cart;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Track;

public class CartTest {
    public static void main(String[] args) {
        Cart cart = new Cart();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc(
                "The Lion King",
                "Animation",
                "Roger Allers",
                87,
                19.95f
        );

        DigitalVideoDisc dvd2 = new DigitalVideoDisc(
                "Star Wars",
                "Science Fiction",
                "George Lucas",
                87,
                24.95f
        );

        Book book = new Book(1, "Clean Code", "Programming", 25.5f);

        CompactDisc cd = new CompactDisc("Greatest Hits", "Music", 15.5f, "Artist A");
        cd.addTrack(new Track("Track 1", 4));
        cd.addTrack(new Track("Track 2", 5));

        cart.addMedia(dvd1);
        cart.addMedia(dvd2);
        cart.addMedia(book);
        cart.addMedia(cd);

        System.out.println();
        cart.print();

        System.out.println("\nSearch by title = Clean Code");
        cart.searchByTitle("Clean Code");

        System.out.println("\nSort by title then cost");
        cart.sortByTitleCost();
        cart.print();

        System.out.println("\nSort by cost then title");
        cart.sortByCostTitle();
        cart.print();
    }
}