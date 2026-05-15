package hust.soict.dsai.aims.screen.manager;

import javax.swing.*;
import java.awt.*;
import hust.soict.dsai.aims.store.Store;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    public AddDigitalVideoDiscToStoreScreen(Store store) {
        super(store, "Add DVD");
    }

    @Override
    protected JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(5, 2, 10, 10)); // 5 hàng cho Title, Category, Director, Length, Cost [cite: 407]

        center.add(new JLabel("Title: "));
        center.add(new JTextField());
        center.add(new JLabel("Category: "));
        center.add(new JTextField());
        center.add(new JLabel("Director: "));
        center.add(new JTextField());
        center.add(new JLabel("Length: "));
        center.add(new JTextField());
        center.add(new JLabel("Cost: "));
        center.add(new JTextField());

        return center;
    }
}