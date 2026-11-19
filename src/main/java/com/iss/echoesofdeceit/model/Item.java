package com.iss.echoesofdeceit.model;

import java.util.Objects;

public class Item {
    private String name;
    private String description;
    private String image;

    public Item(){

    }
    public Item(String name, String description, String image){
        this.name = name;
        this.description = description;
        this.image = image;
    }


    // Getters and Setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    // Override equals e hashCode
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;  // Se sono lo stesso oggetto
        if (obj == null || getClass() != obj.getClass()) return false;  // Se l'oggetto è null o di tipo diverso
        Item item = (Item) obj;
        return name != null && name.equals(item.name);  // Confronta il campo name
    }


    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;  // Restituisce il codice hash basato su name
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
