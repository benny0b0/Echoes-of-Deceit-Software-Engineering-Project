package com.iss.echoesofdeceit.controller;

import com.iss.echoesofdeceit.model.GameModel;
import com.iss.echoesofdeceit.model.StageModel;

public class StageController implements Observer{
    private final StageModel stageModel;

    public StageController(GameModel gameModel, StageModel stageModel) {
        this.stageModel = stageModel;
        gameModel.addObserver(this); // Si iscrive al Publisher
    }

    @Override
    public void update(String state) {
        stageModel.setCurrentScene(state);
    }
}
