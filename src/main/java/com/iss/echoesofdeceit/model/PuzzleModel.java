package com.iss.echoesofdeceit.model;

import com.iss.echoesofdeceit.controller.PuzzleComposite;

public class PuzzleModel {
    private int id;
    private String title;
    private String description;
    private String solution;
    private String hint;
    private PuzzleComposite stackpane;


    public PuzzleModel(){}

    // Getter e Setter per id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter e Setter per title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter e Setter per description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter e Setter per solution
    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }


    // Getter e Setter per hint
    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    // Getter e Setter per stackpane
    public PuzzleComposite getStackpane() {
        return stackpane;
    }

    public void setStackpane(PuzzleComposite stackpane) {
        this.stackpane = stackpane;
    }

}