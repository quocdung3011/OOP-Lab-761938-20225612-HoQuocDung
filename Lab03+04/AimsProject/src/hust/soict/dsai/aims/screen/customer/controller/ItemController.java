package hust.soict.dsai.aims.screen.customer.controller;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;

public class ItemController {

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPlay;

    @FXML
    private Label lblCost;

    @FXML
    private Label lblTitle;

    private Media media;

    private Cart cart;
    
    public void setData(Media media, Cart cart) {
        this.media = media;
        this.cart = cart; // Lưu giỏ hàng lại
        lblTitle.setText(media.getTitle());
        lblCost.setText(media.getCost() + " $");

        if (media instanceof Playable) {
            btnPlay.setVisible(true);
        } else {
            btnPlay.setVisible(false);
            HBox.setMargin(btnAddToCart, new Insets(0, 0, 0, 0));
        }
    }

    @FXML
    void btnAddToCartClicked(ActionEvent event) {
        cart.addMedia(media); // Thêm đĩa vào giỏ hàng thật
        System.out.println("Đã thêm " + media.getTitle() + " vào giỏ hàng!"); 
    }

    @FXML
    void btnPlayClicked(ActionEvent event) {
        try {
            // Ép kiểu sang Playable để gọi hàm play()
            ((Playable) media).play(); 
        } catch (PlayerException e) {
            // Bắt lỗi và hiện Popup cảnh báo
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setTitle("Lỗi phát Media");
            alert.setHeaderText("Không thể phát đĩa này!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}