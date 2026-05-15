package hust.soict.dsai.aims.screen.manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;

public class MediaStore extends JPanel {
    private Media media;

    public MediaStore(Media media) {
        this.media = media;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Title label
        JLabel lblTitle = new JLabel(media.getTitle(), SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblTitle, BorderLayout.NORTH);

        // Info label
        JLabel lblInfo = new JLabel(
            "<html>Category: " + media.getCategory() 
            + "<br>Cost: " + media.getCost() + " $</html>",
            SwingConstants.CENTER);
        add(lblInfo, BorderLayout.CENTER);

        // Panel buttons
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout());

        // Play button - chỉ hiện nếu media implement Playable
        if (media instanceof Playable) {
            JButton btnPlay = new JButton("Play");
            btnPlay.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JDialog dialog = new JDialog();
                    dialog.setTitle("Playing: " + media.getTitle());
                    dialog.setSize(300, 150);
                    dialog.setLayout(new BorderLayout());
                    
                    JTextArea textArea = new JTextArea();
                    textArea.setEditable(false);
                    
                    // Redirect play output to dialog
                    textArea.setText("Playing: " + media.getTitle() 
                        + "\nLength: " + media.getClass().getSimpleName());
                    
                    dialog.add(new JScrollPane(textArea), BorderLayout.CENTER);
                    dialog.setVisible(true);
                }
            });
            panelButtons.add(btnPlay);
        }

        add(panelButtons, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(200, 150));
    }
}