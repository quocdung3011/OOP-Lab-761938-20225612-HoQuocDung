package hust.soict.dsai.test.cart;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.disc.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.Track;
import hust.soict.dsai.aims.exception.PlayerException; 
public class CartTest {
    public static void main(String[] args) {
        Cart cart = new Cart();

        // Add DVD
        DigitalVideoDisc dvd = new DigitalVideoDisc("The Lion King",
                "Animation", "Roger Allers", 87, 19.95f);
        cart.addMedia(dvd);

        // Add Book
        Book book = new Book("Harry Potter", "Fantasy", 29.99f);
        book.addAuthor("J.K. Rowling");
        cart.addMedia(book);

        // Add CD
        CompactDisc cd = new CompactDisc("Thriller", "Pop",
                "John Landis", "Michael Jackson", 15.99f);
        cd.addTrack(new Track("Thriller", 5));
        cd.addTrack(new Track("Billie Jean", 4));
        cart.addMedia(cd);

        // Test print
        cart.print();

        // Test play (Đã sửa lại với khối try-catch)
        System.out.println("\n--- Play ---");
        try {
            dvd.play();
        } catch (PlayerException e) {
            System.err.println("Lỗi khi phát DVD: " + e.getMessage());
        }

        try {
            cd.play();
        } catch (PlayerException e) {
            System.err.println("Lỗi khi phát CD: " + e.getMessage());
        }

        // Test Polymorphism với toString()
        System.out.println("\n--- Polymorphism ---");
        java.util.ArrayList<hust.soict.dsai.aims.media.Media> mediaList 
            = new java.util.ArrayList<>();
        mediaList.add(dvd);
        mediaList.add(book);
        mediaList.add(cd);

        for (hust.soict.dsai.aims.media.Media m : mediaList) {
            System.out.println(m.toString());
        }
    }
}