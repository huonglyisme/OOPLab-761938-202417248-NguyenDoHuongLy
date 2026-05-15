package hust.soict.dsai.aims.screen.manager;

import javax.swing.JPanel;

import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    public AddDigitalVideoDiscToStoreScreen(Store store) {
        super(store, "Add DVD");
    }

    @Override
    protected void buildForm(JPanel form) {
        addInputField(form, "title", "Title:");
        addInputField(form, "category", "Category:");
        addInputField(form, "director", "Director:");
        addInputField(form, "length", "Length:");
        addInputField(form, "cost", "Cost:");
    }

    @Override
    protected Media createMedia() {
        String title = getText("title");
        String category = getText("category");
        String director = getText("director");
        int length = getInt("length");
        float cost = getFloat("cost");

        return new DigitalVideoDisc(title, category, director, length, cost);
    }
}
