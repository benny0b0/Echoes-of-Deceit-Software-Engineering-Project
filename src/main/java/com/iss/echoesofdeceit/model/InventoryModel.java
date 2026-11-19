package com.iss.echoesofdeceit.model;

import java.util.ArrayList;
import java.util.List;

public class InventoryModel {
    private List<Item> items;

    public InventoryModel(){
        this.items = new ArrayList<>();
    }

    // Getters and Setters
    public List<Item> getItems() {
        return this.items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }

}