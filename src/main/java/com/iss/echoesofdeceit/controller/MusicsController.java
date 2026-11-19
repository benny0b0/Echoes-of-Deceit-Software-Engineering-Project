package com.iss.echoesofdeceit.controller;

import com.iss.echoesofdeceit.model.MusicsManager;
import com.iss.echoesofdeceit.model.GameModel;

public class MusicsController implements Observer {
    private final MusicsManager musicsManager;

    public MusicsController(GameModel gameModel, MusicsManager musicsManager) {
        this.musicsManager = musicsManager;
        gameModel.addObserver(this); // Si iscrive al Subject
    }

    @Override
    public void update(String state) {
        musicsManager.playMusic(state);
    }
}
