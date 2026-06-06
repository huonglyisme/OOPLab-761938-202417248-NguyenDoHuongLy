package hust.soict.dsai.aims.screen.customer.controller;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.store.Store;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CartController {

    private Store store;
    private Cart cart;

    private ObservableList<Media> cartMediaList;
    private FilteredList<Media> filteredMediaList;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TableColumn<Media, Integer> colMediaId;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediaCategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private Label costLabel;

    @FXML
    private TextField tfFilter;

    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;

    public CartController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
    }

    @FXML
    public void initialize() {
        colMediaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        ToggleGroup filterGroup = new ToggleGroup();
        radioBtnFilterId.setToggleGroup(filterGroup);
        radioBtnFilterTitle.setToggleGroup(filterGroup);
        radioBtnFilterId.setSelected(true);

        cartMediaList = FXCollections.observableArrayList(cart.getItemsOrdered());
        filteredMediaList = new FilteredList<>(cartMediaList, media -> true);
        tblMedia.setItems(filteredMediaList);

        updateCartView();

        btnPlay.setVisible(false);
        btnPlay.setManaged(false);

        btnRemove.setVisible(false);
        btnRemove.setManaged(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        updateButtonBar(newValue);
                    }
                }
        );

        tfFilter.textProperty().addListener(
                (observable, oldValue, newValue) -> showFilteredMedia(newValue)
        );

        radioBtnFilterId.setOnAction(event -> showFilteredMedia(tfFilter.getText()));
        radioBtnFilterTitle.setOnAction(event -> showFilteredMedia(tfFilter.getText()));
    }

    private void updateCartView() {
        cartMediaList.setAll(cart.getItemsOrdered());
        costLabel.setText(cart.totalCost() + " $");
        showFilteredMedia(tfFilter.getText());
    }

    private void showFilteredMedia(String filterText) {
        filteredMediaList.setPredicate(media -> {
            if (filterText == null || filterText.isBlank()) {
                return true;
            }

            String lowerCaseFilter = filterText.toLowerCase();

            if (radioBtnFilterId.isSelected()) {
                return String.valueOf(media.getId()).contains(lowerCaseFilter);
            }

            if (radioBtnFilterTitle.isSelected()) {
                return media.getTitle().toLowerCase().contains(lowerCaseFilter);
            }

            return true;
        });
    }

    private void updateButtonBar(Media media) {
        btnRemove.setVisible(true);
        btnRemove.setManaged(true);

        if (media instanceof Playable) {
            btnPlay.setVisible(true);
            btnPlay.setManaged(true);
        } else {
            btnPlay.setVisible(false);
            btnPlay.setManaged(false);
        }
    }

    @FXML
    public void btnRemovePressed(ActionEvent event) {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();

        if (selectedMedia != null) {
            cart.removeMedia(selectedMedia);
            updateCartView();

            btnPlay.setVisible(false);
            btnPlay.setManaged(false);

            btnRemove.setVisible(false);
            btnRemove.setManaged(false);
        }
    }

    @FXML
    public void btnPlayPressed(ActionEvent event) {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();

        if (selectedMedia instanceof Playable) {
            try {
                ((Playable) selectedMedia).play();
            } catch (PlayerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Play Error");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void btnPlaceOrderPressed(ActionEvent event) {
        if (cart.getItemsOrdered().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Cart");
            alert.setHeaderText(null);
            alert.setContentText("Your cart is empty.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Placed");
        alert.setHeaderText(null);
        alert.setContentText("Your order has been placed successfully.");
        alert.showAndWait();

        ObservableList<Media> copiedItems = FXCollections.observableArrayList(cart.getItemsOrdered());
        for (Media media : copiedItems) {
            cart.removeMedia(media);
        }

        updateCartView();

        btnPlay.setVisible(false);
        btnPlay.setManaged(false);

        btnRemove.setVisible(false);
        btnRemove.setManaged(false);
    }

    @FXML
    public void btnViewStorePressed(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                    "/hust/soict/dsai/aims/screen/customer/view/Store.fxml"
            ));

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

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("AIMS Store");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
