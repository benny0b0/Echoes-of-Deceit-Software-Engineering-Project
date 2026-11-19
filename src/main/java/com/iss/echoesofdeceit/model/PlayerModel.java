package com.iss.echoesofdeceit.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerModel {
    private static PlayerModel instance;
    private String name;
    private Map<String, String> currentDistrict;
    private int remainingDays;
    private InventoryModel inventory;
    private NotebookModel notebook;
    private Map<String, Boolean> completedPuzzles; // Aggiornato a Map<String, Boolean>

    private List<Item> localItemList; // Inventario locale

    private PlayerModel(){
        this.inventory = new InventoryModel();
        this.notebook= new NotebookModel();
        this.name="Nome Utente";
        this.remainingDays = 5;
        this.currentDistrict = new HashMap<>();
        this.completedPuzzles = new HashMap<>();
        this.localItemList = new ArrayList<>();
    }

    public static PlayerModel getInstance() {
        if(instance==null){
                instance = new PlayerModel();
        }
        return instance;
    }

    // Getters and Setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getCurrentDistrict() {
        return this.currentDistrict;
    }

    public void setCurrentDistrict(Map<String, String> currentDistrict) {
        this.currentDistrict = currentDistrict;
    }

    public int getRemainingDays() {
        return this.remainingDays;
    }

    public void setRemainingDays(int remainingDays) {
        this.remainingDays = remainingDays;
    }

    public InventoryModel getInventory() {
        return this.inventory;
    }

    public void setInventory(InventoryModel inventory) {
        this.inventory = inventory;
    }

    public NotebookModel getNotebook() {
        return this.notebook;
    }

    public void setNotebook(NotebookModel notebook) {
        this.notebook = notebook;
    }

    public Map<String, Boolean> getCompletedPuzzles() {
        return this.completedPuzzles;
    }

    public void setCompletedPuzzles(Map<String, Boolean> completedPuzzles) {
        this.completedPuzzles = completedPuzzles;
    }

    @Override
    public String toString() {
        return "\n\nPLAYERMODEL {" +
                "name='" + name + "\n" +
                ", currentDistrict=" + currentDistrict + "\n" +
                ", remainingDays=" + remainingDays + "\n" +
                ", inventory=" + inventory + "\n" +
                ", localList= "+ localItemList+ "\n" +
                ", notebook=" + notebook + "\n" +
                ", completedPuzzles=" + completedPuzzles +
                "}\n\n";
    }

    public List<Item> getLocalItemList() {
        return localItemList;
    }

    public void setLocalItemList(List<Item> localItemList) {
        this.localItemList = localItemList;
    }
}
