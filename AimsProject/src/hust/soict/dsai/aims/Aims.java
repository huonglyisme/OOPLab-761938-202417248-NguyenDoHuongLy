package hust.soict.dsai.aims;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.media.Track;
import hust.soict.dsai.aims.store.Store;

import java.util.Scanner;

public class Aims {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Store store = new Store();
    private static final Cart cart = new Cart();
    private static int nextBookId = 1000;

    public static void main(String[] args) {
        seedStore();

        boolean running = true;
        while (running) {
            showMenu();
            int choice = readInt("Choose: ");

            switch (choice) {
                case 1:
                    handleViewStore();
                    break;
                case 2:
                    handleUpdateStore();
                    break;
                case 3:
                    handleCart();
                    break;
                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void showMenu() {
        System.out.println("AIMS:");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
    }

    public static void storeMenu() {
        System.out.println("Options:");
        System.out.println("--------------------------------");
        System.out.println("1. See a media's details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
    }

    public static void mediaDetailsMenu() {
        System.out.println("Options:");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
    }

    public static void cartMenu() {
        System.out.println("Options:");
        System.out.println("--------------------------------");
        System.out.println("1. Filter media in cart");
        System.out.println("2. Sort media in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
    }

    public static void updateStoreMenu() {
        System.out.println("Options:");
        System.out.println("--------------------------------");
        System.out.println("1. Add a media to store");
        System.out.println("2. Remove a media from store");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
    }

    private static void handleViewStore() {
        boolean back = false;

        while (!back) {
            System.out.println();
            store.printStore();
            storeMenu();
            int choice = readInt("Choose: ");

            switch (choice) {
                case 1:
                    handleMediaDetailsInStore();
                    break;
                case 2:
                    addMediaFromStoreToCart();
                    break;
                case 3:
                    playMediaInStore();
                    break;
                case 4:
                    handleCart();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void handleMediaDetailsInStore() {
        String title = readLine("Enter title: ");
        Media media = store.findMediaByTitle(title);

        if (media == null) {
            System.out.println("Media not found.");
            return;
        }

        System.out.println(media);

        boolean back = false;
        while (!back) {
            mediaDetailsMenu();
            int choice = readInt("Choose: ");

            switch (choice) {
                case 1:
                    cart.addMedia(media);
                    break;
                case 2:
                    playMedia(media);
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addMediaFromStoreToCart() {
        String title = readLine("Enter title to add to cart: ");
        Media media = store.findMediaByTitle(title);

        if (media == null) {
            System.out.println("Media not found.");
            return;
        }

        cart.addMedia(media);
    }

    private static void playMediaInStore() {
        String title = readLine("Enter title to play: ");
        Media media = store.findMediaByTitle(title);

        if (media == null) {
            System.out.println("Media not found.");
            return;
        }

        playMedia(media);
    }

    private static void handleUpdateStore() {
        boolean back = false;

        while (!back) {
            updateStoreMenu();
            int choice = readInt("Choose: ");

            switch (choice) {
                case 1:
                    addMediaToStore();
                    break;
                case 2:
                    removeMediaFromStore();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addMediaToStore() {
        System.out.println("Choose media type:");
        System.out.println("1. Book");
        System.out.println("2. DVD");
        System.out.println("3. CD");
        int type = readInt("Choose: ");

        switch (type) {
            case 1:
                addBookToStore();
                break;
            case 2:
                addDvdToStore();
                break;
            case 3:
                addCdToStore();
                break;
            default:
                System.out.println("Invalid media type.");
        }
    }

    private static void addBookToStore() {
        String title = readLine("Book title: ");
        String category = readLine("Category: ");
        float cost = readFloat("Cost: ");

        Book book = new Book(nextBookId++, title, category, cost);

        int n = readInt("Number of authors: ");
        for (int i = 0; i < n; i++) {
            String author = readLine("Author " + (i + 1) + ": ");
            book.addAuthor(author);
        }

        store.addMedia(book);
    }

    private static void addDvdToStore() {
        String title = readLine("DVD title: ");
        String category = readLine("Category: ");
        String director = readLine("Director: ");
        int length = readInt("Length: ");
        float cost = readFloat("Cost: ");

        DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, director, length, cost);
        store.addMedia(dvd);
    }

    private static void addCdToStore() {
        String title = readLine("CD title: ");
        String category = readLine("Category: ");
        String artist = readLine("Artist: ");
        float cost = readFloat("Cost: ");

        CompactDisc cd = new CompactDisc(title, category, cost, artist);

        int n = readInt("Number of tracks: ");
        for (int i = 0; i < n; i++) {
            String trackTitle = readLine("Track " + (i + 1) + " title: ");
            int trackLength = readInt("Track " + (i + 1) + " length: ");
            cd.addTrack(new Track(trackTitle, trackLength));
        }

        store.addMedia(cd);
    }

    private static void removeMediaFromStore() {
        String title = readLine("Enter title to remove from store: ");
        Media media = store.findMediaByTitle(title);

        if (media == null) {
            System.out.println("Media not found.");
            return;
        }

        store.removeMedia(media);
    }

    private static void handleCart() {
        boolean back = false;

        while (!back) {
            System.out.println();
            cart.print();
            cartMenu();
            int choice = readInt("Choose: ");

            switch (choice) {
                case 1:
                    filterMediaInCart();
                    break;
                case 2:
                    sortMediaInCart();
                    break;
                case 3:
                    removeMediaFromCart();
                    break;
                case 4:
                    playMediaInCart();
                    break;
                case 5:
                    cart.placeOrder();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void filterMediaInCart() {
        System.out.println("Filter by:");
        System.out.println("1. Id");
        System.out.println("2. Title");
        int choice = readInt("Choose: ");

        switch (choice) {
            case 1:
                int id = readInt("Enter id: ");
                cart.searchById(id);
                break;
            case 2:
                String title = readLine("Enter title: ");
                cart.searchByTitle(title);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void sortMediaInCart() {
        System.out.println("Sort by:");
        System.out.println("1. Title then cost");
        System.out.println("2. Cost then title");
        int choice = readInt("Choose: ");

        switch (choice) {
            case 1:
                cart.sortByTitleCost();
                System.out.println("Sorted by title then cost.");
                cart.print();
                break;
            case 2:
                cart.sortByCostTitle();
                System.out.println("Sorted by cost then title.");
                cart.print();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void removeMediaFromCart() {
        String title = readLine("Enter title to remove from cart: ");
        Media media = findMediaInCartByTitle(title);

        if (media == null) {
            System.out.println("Media not found in cart.");
            return;
        }

        cart.removeMedia(media);
    }

    private static void playMediaInCart() {
        String title = readLine("Enter title to play: ");
        Media media = findMediaInCartByTitle(title);

        if (media == null) {
            System.out.println("Media not found in cart.");
            return;
        }

        playMedia(media);
    }

    private static Media findMediaInCartByTitle(String title) {
        for (Media media : cart.getItemsOrdered()) {
            if (media.getTitle() != null &&
                    media.getTitle().equalsIgnoreCase(title.trim())) {
                return media;
            }
        }
        return null;
    }

    private static void playMedia(Media media) {
        if (media instanceof Playable) {
            ((Playable) media).play();
        } else {
            System.out.println("This media cannot be played.");
        }
    }

    private static void seedStore() {
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

        Book book1 = new Book(nextBookId++, "Clean Code", "Programming", 25.5f);
        book1.addAuthor("Robert C. Martin");

        CompactDisc cd1 = new CompactDisc("Greatest Hits", "Music", 15.5f, "Artist A");
        cd1.addTrack(new Track("Track 1", 4));
        cd1.addTrack(new Track("Track 2", 5));

        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(book1);
        store.addMedia(cd1);
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer.");
            }
        }
    }

    private static float readFloat(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Float.parseFloat(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
