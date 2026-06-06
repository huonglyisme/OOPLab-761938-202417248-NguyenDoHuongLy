package hust.soict.dsai.aims.screen.customer.controller;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ItemController {

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblCost;

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPlay;

    private Media media;
    private Cart cart;

    public void setData(Media media, Cart cart) {
        this.media = media;
        this.cart = cart;

        lblTitle.setText(media.getTitle());
        lblCost.setText(media.getCost() + " $");

        if (media instanceof Playable) {
            btnPlay.setVisible(true);
            btnPlay.setManaged(true);
        } else {
            btnPlay.setVisible(false);
            btnPlay.setManaged(false);
        }
    }

    @FXML
    void btnAddToCartClicked() {
        cart.addMedia(media);
        System.out.println("Added to cart: " + media.getTitle());
    }

    @FXML
    void btnPlayClicked() {
        System.out.println("Play: " + media.getTitle());
    }
}

