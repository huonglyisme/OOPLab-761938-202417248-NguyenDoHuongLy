package hust.soict.dsai.test.screen.customer.store;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.screen.customer.controller.ViewStoreController;
import hust.soict.dsai.aims.store.Store;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class TestViewStoreScreen extends Application {

    private static Store store;
    private static Cart cart;

    @Override
    public void start(Stage stage) throws Exception {
        final URL fxmlUrl = getClass().getResource(
                "/hust/soict/dsai/aims/screen/customer/view/Store.fxml"
        );

        FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);

        fxmlLoader.setControllerFactory(controllerClass -> {
            if (controllerClass == ViewStoreController.class) {
                return new ViewStoreController(store, cart);
            }

            try {
                return controllerClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setTitle("AIMS Store");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        store = new Store();
        cart = new Cart();

        store.addMedia(new DigitalVideoDisc(
                "The Lion King",
                "Animation",
                "Roger Allers",
                87,
                19.95f
        ));

        store.addMedia(new DigitalVideoDisc(
                "Star Wars",
                "Science Fiction",
                "George Lucas",
                87,
                24.95f
        ));

        store.addMedia(new DigitalVideoDisc(
                "Aladdin",
                "Animation",
                "John Musker",
                90,
                18.99f
        ));

        store.addMedia(new DigitalVideoDisc(
                "Broken DVD",
                "Test",
                "Unknown",
                0,
                5.0f
        ));

        store.addMedia(new Book(
                4,
                "Harry Potter",
                "Fantasy",
                10.5f
        ));

        store.addMedia(new Book(
                5,
                "Clean Code",
                "Programming",
                15.0f
        ));

        launch(args);
    }
}
