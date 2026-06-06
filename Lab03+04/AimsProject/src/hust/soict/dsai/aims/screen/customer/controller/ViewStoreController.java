package hust.soict.dsai.aims.screen.customer.controller;

import java.io.IOException;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class ViewStoreController {

    @FXML
    private GridPane gridPane;

    private Store store;

    private Cart cart = new Cart();
    // Constructor nhận vào dữ liệu Store từ hệ thống
    public ViewStoreController(Store store) {
        this.store = store;
    }

    @FXML
    public void initialize() {
        final String ITEM_FXML_FILE_PATH = "/hust/soict/dsai/aims/screen/customer/view/Item.fxml";
        int column = 0;
        int row = 1;
        
        for (int i = 0; i < store.getItemsInStore().size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource(ITEM_FXML_FILE_PATH));
                ItemController itemController = new ItemController();
                fxmlLoader.setController(itemController);
                AnchorPane anchorPane = new AnchorPane();
                anchorPane = fxmlLoader.load();
                
                // Nạp dữ liệu vào ô sản phẩm
                itemController.setData(store.getItemsInStore().get(i), cart);
                
                // Thuật toán chia lưới: 3 sản phẩm 1 hàng
                if (column == 3) {
                    column = 0;
                    row++;
                }
                
                gridPane.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(20, 10, 10, 10));
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void btnViewCartPressed(ActionEvent event) {
        try {
            // Nạp file FXML của màn hình Cart
            final String CART_FXML_FILE_PATH = "/hust/soict/dsai/aims/screen/customer/view/Cart.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(CART_FXML_FILE_PATH));
            
            // Khởi tạo CartController với 1 giỏ hàng trống (tạm thời)
            CartController cartController = new CartController(this.cart);
            fxmlLoader.setController(cartController);
            javafx.scene.Parent root = fxmlLoader.load();
            
            // Lấy Stage (Cửa sổ) hiện tại và đổi Scene (Cảnh) sang Cart
            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
            stage.setTitle("Cart");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}