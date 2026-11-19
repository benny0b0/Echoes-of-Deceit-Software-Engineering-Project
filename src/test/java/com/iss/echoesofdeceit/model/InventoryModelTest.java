package com.iss.echoesofdeceit.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class InventoryModelTest {

    @Test
    void testConstructor() {
        InventoryModel inventory = new InventoryModel();
        assertNotNull(inventory.getItems());
        assertTrue(inventory.getItems().isEmpty());
    }

    @Test
    void testSetItems() {
        InventoryModel inventory = new InventoryModel();

        List<Item> items = new ArrayList<>();
        items.add(new Item());
        items.add(new Item());

        inventory.setItems(items);

        assertNotNull(inventory.getItems());
        assertEquals(2, inventory.getItems().size());
    }

    @Test
    void testGetItems() {
        InventoryModel inventory = new InventoryModel();

        List<Item> items = new ArrayList<>();
        Item item1 = new Item();
        Item item2 = new Item();
        items.add(item1);
        items.add(item2);

        inventory.setItems(items);

        List<Item> retrievedItems = inventory.getItems();
        assertNotNull(retrievedItems);
        assertEquals(2, retrievedItems.size());
        assertSame(item1, retrievedItems.get(0));
        assertSame(item2, retrievedItems.get(1));
    }

    @Test
    void testModifyItemsDirectly() {
        InventoryModel inventory = new InventoryModel();
        List<Item> items = inventory.getItems();

        Item newItem = new Item();
        items.add(newItem);

        assertEquals(1, inventory.getItems().size());
        assertSame(newItem, inventory.getItems().get(0));
    }
}
