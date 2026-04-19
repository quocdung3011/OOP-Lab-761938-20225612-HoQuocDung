package hust.soict.dsai.garbage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GarbageCreator {
    public static void main(String[] args) throws IOException {
        // Dùng + operator - chậm, tạo nhiều garbage
        byte[] inputBytes = "Hello World Test String ".repeat(1000)
                           .getBytes();
        
        long startTime = System.currentTimeMillis();
        String outputString = "";
        for (byte b : inputBytes) {
            outputString += (char) b;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time with +: " + (endTime - startTime) + "ms");

        // Dùng StringBuilder - nhanh, ít garbage
        startTime = System.currentTimeMillis();
        StringBuilder outputSB = new StringBuilder();
        for (byte b : inputBytes) {
            outputSB.append((char) b);
        }
        endTime = System.currentTimeMillis();
        System.out.println("Time with StringBuilder: " 
            + (endTime - startTime) + "ms");
    }
}