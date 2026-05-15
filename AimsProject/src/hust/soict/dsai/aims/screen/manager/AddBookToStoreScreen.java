package hust.soict.dsai.aims.screen.manager;

import javax.swing.JPanel;

import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    public AddBookToStoreScreen(Store store) {
        super(store, "Add Book");
    }

    @Override
    protected void buildForm(JPanel form) {
        addInputField(form, "title", "Title:");
        addInputField(form, "category", "Category:");
        addInputField(form, "cost", "Cost:");
        addInputField(form, "authors", "Authors, separated by comma:");
    }

    @Override
    protected Media createMedia() {
        String title = getText("title");
        String category = getText("category");
        float cost = getFloat("cost");

        int id = store.getItemsInStore().size() + 1;
        Book book = new Book(id, title, category, cost);

        String authorsText = getText("authors");
        if (!authorsText.isEmpty()) {
            String[] authors = authorsText.split(",");

            for (String author : authors) {
                String trimmedAuthor = author.trim();

                if (!trimmedAuthor.isEmpty()) {
                    book.addAuthor(trimmedAuthor);
                }
            }
        }

        return book;
    }
}
