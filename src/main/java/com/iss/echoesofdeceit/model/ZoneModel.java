package com.iss.echoesofdeceit.model;

import java.util.List;

public class ZoneModel {
    private List<PuzzleModel> puzzleModel;
    private ExplorationModel explorationModel;
    private List<DialogModel> dialogModels;
    private int numberOfItems;

    // Getter e Setter
    public List<PuzzleModel> getPuzzle() {
        return puzzleModel;
    }
    public void setPuzzle(List<PuzzleModel> puzzleModel) {
        this.puzzleModel = puzzleModel;
    }

    public ExplorationModel getExploration() {
        return explorationModel;
    }

    public void setExploration(ExplorationModel explorationModel) {
        this.explorationModel = explorationModel;
    }

    public List<DialogModel> getDialogs() {
        return dialogModels;
    }

    public void setDialogs(List<DialogModel> dialogModels) {
        this.dialogModels = dialogModels;
    }

    public int getNumberOfItems() {
        return this.numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

}