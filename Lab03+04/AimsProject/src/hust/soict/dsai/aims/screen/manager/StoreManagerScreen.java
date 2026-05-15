package hust.soict.dsai.aims.screen.manager;

import java.awt.*;
import javax.swing.*;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;

public class StoreManagerScreen extends JFrame {
    private Store store;

   JPanel createNorth() {
	   JPanel north = new JPanel();
	   north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
	   north.add(createMenuBar());
	   north.add(createHeader());
	   return north;
   }
   
   JMenuBar createMenuBar() {
	   JMenu menu = new JMenu("Options");  

	   menu.add(new JMenuItem("View store"));

	   JMenu smUpdateStore = new JMenu("Update Store");
	   smUpdateStore.add(new JMenuItem("Add Book"));
	   smUpdateStore.add(new JMenuItem("Add CD"));
	   smUpdateStore.add(new JMenuItem("Add DVD"));
	   menu.add(smUpdateStore);

	   JMenuBar menuBar = new JMenuBar();
	   menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	   menuBar.add(menu);

	   return menuBar;
   }
   
   JPanel createHeader() {
	   JPanel header = new JPanel();
	   header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));  

	   JLabel title = new JLabel("AIMS");
	   title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
	   title.setForeground(Color.CYAN);

	   header.add(Box.createRigidArea(new Dimension(10, 10)));
	   header.add(title);
	   header.add(Box.createHorizontalGlue());
	   header.add(Box.createRigidArea(new Dimension(10, 10)));

	   return header;
   }
   
   JPanel createCenter() {
	    JPanel center = new JPanel();
	    // Sử dụng GridLayout(3, 3) với khoảng cách giữa các ô là 2 pixel
	    center.setLayout(new GridLayout(3, 3, 2, 2)); 

	    java.util.ArrayList<Media> mediaInStore = store.getItemsInStore();
	    // Tài liệu yêu cầu hiển thị tối đa 9 item cho lưới 3x3
	    for (int i = 0; i < mediaInStore.size() && i < 9; i++) {
	        MediaStore cell = new MediaStore(mediaInStore.get(i));
	        center.add(cell);
	    }

	    return center;
	}
   
   public StoreManagerScreen(Store store) {
	    this.store = store;
	    Container cp = getContentPane();
	    cp.setLayout(new BorderLayout());

	    cp.add(createNorth(), BorderLayout.NORTH); // Thêm phần Menu và Header
	    cp.add(createCenter(), BorderLayout.CENTER); // Thêm lưới Media

	    setTitle("Store Manager");
	    setSize(1024, 768);
	    setLocationRelativeTo(null); // Hiển thị cửa sổ ở giữa màn hình
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true); // Quan trọng: Phải set true để thấy giao diện
	}
   
   public static void main(String[] args) {
	    Store store = new Store();
	    
	    // Nạp dữ liệu mẫu để giao diện có nội dung hiển thị [cite: 412]
	    hust.soict.dsai.aims.disc.DigitalVideoDisc dvd1 = new hust.soict.dsai.aims.disc.DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
	    hust.soict.dsai.aims.disc.DigitalVideoDisc dvd2 = new hust.soict.dsai.aims.disc.DigitalVideoDisc("Star Wars", "Sci-Fi", "George Lucas", 87, 24.95f);
	    hust.soict.dsai.aims.disc.DigitalVideoDisc dvd3 = new hust.soict.dsai.aims.disc.DigitalVideoDisc("Aladin", "Animation", "Ron Clements", 90, 18.99f);
	    
	    store.addMedia(dvd1);
	    store.addMedia(dvd2);
	    store.addMedia(dvd3);

	    new StoreManagerScreen(store);
	}
   
}