package hust.soict.dsai.aims.screen.manager;

import javax.swing.JPanel;

import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    public AddCompactDiscToStoreScreen(Store store) {
        super(store, "Add CD");
    }

    @Override
    protected void buildForm(JPanel form) {
        addInputField(form, "title", "Title:");
        addInputField(form, "category", "Category:");
        addInputField(form, "cost", "Cost:");
        addInputField(form, "artist", "Artist:");
        addInputField(form, "director", "Director:");
        addInputField(form, "length", "Length:");
    }

    @Override
    protected Media createMedia() {
        String title = getText("title");
        String category = getText("category");
        float cost = getFloat("cost");
        String artist = getText("artist");
        String director = getText("director");
        int length = getInt("length");

        return new CompactDisc(title, category, cost, artist, director, length);
    }
}
