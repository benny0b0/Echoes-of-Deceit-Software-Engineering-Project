package com.iss.echoesofdeceit.model;

import com.iss.echoesofdeceit.controller.Observer;

import java.util.ArrayList;
import java.util.List;

public class GameModel implements Subject{
    private List<Observer> observers;
    private String gameScene;
    private int currentPuzzle=0;
    private int currentZone;
    private int currentDialog;
    private String currentFocusItemBackground;
    private String currentDistrict;
    private static GameModel instance;

    private GameModel() {
        observers = new ArrayList<>();
    }


    public static GameModel getInstance() {
        if(instance==null){
            instance = new GameModel();
        }
        return instance;
    }

    public void setGameScene(String gameScene) {
        this.gameScene = gameScene;
        notifyObserver();
    }

    public String getGameScene() {
        return gameScene;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update(gameScene);
        }
    }

    public int getCurrentPuzzle() {
        return currentPuzzle;
    }

    public void setCurrentPuzzle(int currentPuzzle) {
        this.currentPuzzle = currentPuzzle;
    }

    public int getCurrentZone() {
        return currentZone;
    }

    public void setCurrentZone(int currentZone) {
        this.currentZone = currentZone;
    }

    public int getCurrentDialog() {
        return currentDialog;
    }

    public void setCurrentDialog(int currentDialog) {
        this.currentDialog = currentDialog;
    }

    public String getCurrentDistrict() {
        return currentDistrict;
    }

    public void setCurrentDistrict(String currentDistrict) {
        this.currentDistrict = currentDistrict;
    }

    public String getCurrentFocusItemBackground() {
        return this.currentFocusItemBackground;
    }

    public void setCurrentFocusItemBackground(String currentFocusItemBackground) {
        this.currentFocusItemBackground = currentFocusItemBackground;
    }
}
