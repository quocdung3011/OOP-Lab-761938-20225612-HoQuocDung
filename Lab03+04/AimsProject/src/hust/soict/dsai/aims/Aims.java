package hust.soict.dsai.aims;

import java.util.Scanner;
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.disc.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;

public class Aims {
    static Scanner scanner = new Scanner(System.in);
    static Store store = new Store();
    static Cart cart = new Cart();

    public static void main(String[] args) {
        // Thêm vài DVD mẫu vào store
        store.addMedia(new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f));
        store.addMedia(new DigitalVideoDisc("Star Wars", "Sci-Fi", "George Lucas", 87, 24.95f));
        store.addMedia(new DigitalVideoDisc("Aladin", "Animation", "Ron Clements", 90, 18.99f));

        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: viewStore(); break;
                case 2: updateStore(); break;
                case 3: seeCart(); break;
                case 0:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void showMenu() {
        System.out.println("\nAIMS:");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3: ");
    }

    public static void viewStore() {
        System.out.println("\n--- STORE ---");
        for (Media m : store.getItemsInStore()) {
            System.out.println(m.toString());
        }
        while (true) {
            storeMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    for (Media m : store.getItemsInStore()) {
                        if (m.getTitle().equalsIgnoreCase(title)) {
                            System.out.println(m.toString());
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter title to add to cart: ");
                    String t = scanner.nextLine();
                    for (Media m : store.getItemsInStore()) {
                        if (m.getTitle().equalsIgnoreCase(t)) {
                            cart.addMedia(m);
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter title to play: ");
                    String pt = scanner.nextLine();
                    for (Media m : store.getItemsInStore()) {
                        if (m.getTitle().equalsIgnoreCase(pt)
                            && m instanceof hust.soict.dsai.aims.media.Playable) {
                            try {
                                ((hust.soict.dsai.aims.media.Playable) m).play();
                            } catch (hust.soict.dsai.aims.exception.PlayerException e) {
                                System.err.println("Cannot play this media: " + e.getMessage());
                            }
                            break;
                        }
                    }
                    break;
                case 4: seeCart(); break;
                case 0: return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    public static void storeMenu() {
        System.out.println("\nOptions:");
        System.out.println("--------------------------------");
        System.out.println("1. See a media's details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3-4: ");
    }

    public static void updateStore() {
        System.out.println("\n1. Add DVD to store");
        System.out.println("2. Remove media from store");
        System.out.print("Choose: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            System.out.print("Title: ");
            String title = scanner.nextLine();
            System.out.print("Category: ");
            String cat = scanner.nextLine();
            System.out.print("Director: ");
            String dir = scanner.nextLine();
            System.out.print("Length: ");
            int len = scanner.nextInt();
            System.out.print("Cost: ");
            float cost = scanner.nextFloat();
            scanner.nextLine();
            store.addMedia(new DigitalVideoDisc(title, cat, dir, len, cost));
        } else if (choice == 2) {
            System.out.print("Enter title to remove: ");
            String t = scanner.nextLine();
            for (Media m : store.getItemsInStore()) {
                if (m.getTitle().equalsIgnoreCase(t)) {
                    store.removeMedia(m);
                    break;
                }
            }
        }
    }

    public static void seeCart() {
        cart.print();
        while (true) {
            cartMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("1. By ID  2. By Title");
                    int fc = scanner.nextInt(); scanner.nextLine();
                    if (fc == 1) {
                        System.out.print("Enter ID: ");
                        cart.searchById(scanner.nextInt());
                        scanner.nextLine();
                    } else {
                        System.out.print("Enter title: ");
                        cart.searchByTitle(scanner.nextLine());
                    }
                    break;
                case 2:
                    System.out.println("1. By Title  2. By Cost");
                    int sc = scanner.nextInt(); scanner.nextLine();
                    if (sc == 1) cart.sortByTitleCost();
                    else cart.sortByCostTitle();
                    cart.print();
                    break;
                case 3:
                    System.out.print("Enter title to play: ");
                    String pt = scanner.nextLine();
                    for (Media m : store.getItemsInStore()) {
                        if (m.getTitle().equalsIgnoreCase(pt)
                            && m instanceof hust.soict.dsai.aims.media.Playable) {
                            try {
                                ((hust.soict.dsai.aims.media.Playable) m).play();
                            } catch (hust.soict.dsai.aims.exception.PlayerException e) {
                                System.err.println("Cannot play this media: " + e.getMessage());
                            }
                            break;
                        }
                    }
                    break;
                case 4:
                    System.out.print("Enter title to play: ");
                    String ptCart = scanner.nextLine();
                    for (Media m : cart.getItemsOrdered()) {
                        if (m.getTitle().equalsIgnoreCase(ptCart)
                            && m instanceof hust.soict.dsai.aims.media.Playable) {
                            try {
                                ((hust.soict.dsai.aims.media.Playable) m).play();
                            } catch (hust.soict.dsai.aims.exception.PlayerException e) {
                                System.err.println("Cannot play this media: " + e.getMessage());
                            }
                            break;
                        }
                    }
                    break;
                case 5:
                    System.out.println("Order created! Cart is now empty.");
                    cart.getItemsOrdered().clear();
                    return;
                case 0: return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    public static void cartMenu() {
        System.out.println("\nOptions:");
        System.out.println("--------------------------------");
        System.out.println("1. Filter media in cart");
        System.out.println("2. Sort media in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3-4-5: ");
    }
}