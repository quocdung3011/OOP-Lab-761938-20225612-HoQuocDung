package aims;

import java.util.Scanner;

public class Aims {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Cart anOrder = new Cart();

        System.out.println("--- Setup your Cart ---");
        System.out.print("How many DVDs do you want to add? ");
        int num = keyboard.nextInt();
        keyboard.nextLine(); 

        for (int i = 0; i < num; i++) {
            System.out.println("\nEnter info for DVD #" + (i + 1));
            
            System.out.print("Title: ");
            String title = keyboard.nextLine();
            
            System.out.print("Category: ");
            String category = keyboard.nextLine();
            
            System.out.print("Cost: ");
            float cost = keyboard.nextFloat();
            keyboard.nextLine(); // Đọc bỏ dòng trống

            // Tạo đối tượng và thêm vào giỏ
            DigitalVideoDisc dvd = new DigitalVideoDisc(category, title, cost);
            anOrder.addDigitalVideoDisc(dvd);
            
            // Kiểm tra ID tự động tăng
            System.out.println("DVD ID: " + dvd.getId());
        }

        System.out.println("\n--- Final Results ---");
        System.out.println("Total Cost is: " + anOrder.totalCost());
        
        
    }
}