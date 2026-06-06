package hust.soict.dsai.aims.screen.customer.controller;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CartController {

    private Cart cart;

    @FXML private TableView<Media> tblMedia;
    @FXML private TableColumn<Media, String> colMediaTitle;
    @FXML private TableColumn<Media, String> colMediaCategory;
    @FXML private TableColumn<Media, Float> colMediaCost;
    @FXML private Button btnPlay;
    @FXML private Button btnRemove;
    @FXML private Button btnPlaceOrder;
    @FXML private Label lblTotalCost;

    public CartController(Cart cart) {
        this.cart = cart;
    }

    @FXML
    public void initialize() {
        // Cấu hình các cột lấy dữ liệu từ các thuộc tính của Media
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));

        // Nạp dữ liệu từ giỏ hàng vào bảng
        if (cart.getItemsOrdered() != null) {
            ObservableList<Media> observableCart = FXCollections.observableArrayList(cart.getItemsOrdered());
            tblMedia.setItems(observableCart);
        }

        // Cập nhật tổng tiền
        lblTotalCost.setText(cart.totalCost() + " $");

        // Ẩn nút Play và Remove khi chưa chọn sản phẩm nào
        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        // Bắt sự kiện khi người dùng click chọn 1 hàng trong bảng
        tblMedia.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Media>() {
            @Override
            public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                if (newValue != null) {
                    btnRemove.setVisible(true);
                    if (newValue instanceof Playable) {
                        btnPlay.setVisible(true);
                    } else {
                        btnPlay.setVisible(false);
                    }
                } else {
                    btnPlay.setVisible(false);
                    btnRemove.setVisible(false);
                }
            }
        });
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        cart.removeMedia(media);
        // Cập nhật lại bảng và tổng tiền
        tblMedia.setItems(FXCollections.observableArrayList(cart.getItemsOrdered()));
        lblTotalCost.setText(cart.totalCost() + " $");
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media != null) {
            try {
                ((Playable) media).play();
            } catch (PlayerException e) {
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                alert.setTitle("Lỗi phát Media");
                alert.setHeaderText("Không thể phát đĩa này!");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        System.out.println("Order placed successfully!");
        cart.getItemsOrdered().clear(); // Xóa giỏ hàng sau khi đặt
        tblMedia.setItems(FXCollections.observableArrayList(cart.getItemsOrdered()));
        lblTotalCost.setText(cart.totalCost() + " $");
    }
}