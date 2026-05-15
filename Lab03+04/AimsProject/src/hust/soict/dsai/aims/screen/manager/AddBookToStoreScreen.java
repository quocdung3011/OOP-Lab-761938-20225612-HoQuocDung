package hust.soict.dsai.aims.screen.manager;

import javax.swing.*;
import java.awt.*;
import hust.soict.dsai.aims.store.Store;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    public AddBookToStoreScreen(Store store) {
        super(store, "Add Book");
    }

    @Override
    protected JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(4, 2, 10, 10)); // Title, Category, Cost, Authors

        center.add(new JLabel("Title: "));
        center.add(new JTextField());
        center.add(new JLabel("Category: "));
        center.add(new JTextField());
        center.add(new JLabel("Cost: "));
        center.add(new JTextField());
        center.add(new JLabel("Authors: "));
        center.add(new JTextField());

        return center;
    }
}