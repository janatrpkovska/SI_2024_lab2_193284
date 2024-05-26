package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a list of items to test the checkCart method
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item 1", "123456", 100, 0.1f));
        items.add(new Item("Item 2", "789012", 200, 0.2f));

        // Call the checkCart method with the list of items and a payment amount
        boolean isPaymentValid = SILab2.checkCart(items, 300);

        // Print the result
        System.out.println("Is payment valid? " + isPaymentValid);
    }
}
