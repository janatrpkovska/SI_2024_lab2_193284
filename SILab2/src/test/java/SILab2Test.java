
package org.example;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    @Test
    void testCheckCartEveryBranch() {
        // Test case where allItems is null
        RuntimeException ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 100));
        assertEquals("allItems list can't be null!", ex.getMessage());

        // Test case with valid items and sufficient payment
        Item item1 = new Item("Item 1", "123456", 100, 0.1f);
        Item item2 = new Item("Item 2", "789012", 200, 0.2f);
        List<Item> items = Arrays.asList(item1, item2);
        assertTrue(SILab2.checkCart(items, 270)); // 10% of 100 + 20% of 200 = 30; 100 + 200 - 30 = 270

        // Test case with an invalid character in the barcode
        Item item3 = new Item("Item 3", "123a56", 100, 0.1f);
        List<Item> itemsInvalidBarcode = Arrays.asList(item3);
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(itemsInvalidBarcode, 100));
        assertEquals("Invalid character in item barcode!", ex.getMessage());

        // Test case with no barcode
        Item item4 = new Item("Item 4", null, 100, 0.1f);
        List<Item> itemsNoBarcode = Arrays.asList(item4);
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(itemsNoBarcode, 100));
        assertEquals("No barcode!", ex.getMessage());

        // Test case with discount and barcode starting with '0'
        Item item5 = new Item("Item 5", "012345", 400, 0.1f); // 400 - 40 = 360 - 30 = 330
        List<Item> itemsWithDiscount = Arrays.asList(item5);
        assertFalse(SILab2.checkCart(itemsWithDiscount, 300));
    }

    @Test
    void testCheckCartMultipleCondition() {
        // Test for condition: (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0')
        // Each test case covers a different combination of conditions

        // All conditions true
        Item item1 = new Item("Item 1", "012345", 400, 0.1f);
        List<Item> items = Arrays.asList(item1);
        assertFalse(SILab2.checkCart(items, 300));

        // First condition false, others true
        Item item2 = new Item("Item 2", "012345", 300, 0.1f);
        items = Arrays.asList(item2);
        assertTrue(SILab2.checkCart(items, 270));

        // Second condition false, others true
        Item item3 = new Item("Item 3", "012345", 400, 0.0f);
        items = Arrays.asList(item3);
        assertTrue(SILab2.checkCart(items, 400));

        // Third condition false, others true
        Item item4 = new Item("Item 4", "112345", 400, 0.1f);
        items = Arrays.asList(item4);
        assertFalse(SILab2.checkCart(items, 360));

        // All conditions false
        Item item5 = new Item("Item 5", "112345", 300, 0.0f);
        items = Arrays.asList(item5);
        assertTrue(SILab2.checkCart(items, 300));
    }
}
