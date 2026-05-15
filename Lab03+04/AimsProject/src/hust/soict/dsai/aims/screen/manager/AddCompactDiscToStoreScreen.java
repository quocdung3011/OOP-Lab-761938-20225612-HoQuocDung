package hust.soict.dsai.aims.screen.manager;

import javax.swing.*;
import java.awt.*;
import hust.soict.dsai.aims.store.Store;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    public AddCompactDiscToStoreScreen(Store store) {
        super(store, "Add CD");
    }

    @Override
    protected JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(5, 2, 10, 10)); // Title, Category, Artist, Director, Cost

        center.add(new JLabel("Title: "));
        center.add(new JTextField());
        center.add(new JLabel("Category: "));
        center.add(new JTextField());
        center.add(new JLabel("Artist: "));
        center.add(new JTextField());
        center.add(new JLabel("Director: "));
        center.add(new JTextField());
        center.add(new JLabel("Cost: "));
        center.add(new JTextField());

        return center;
    }
}