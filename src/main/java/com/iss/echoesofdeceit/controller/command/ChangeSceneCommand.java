package com.iss.echoesofdeceit.controller.command;

import com.iss.echoesofdeceit.model.GameModel;

public class ChangeSceneCommand implements Command{
    private String scene;

    public ChangeSceneCommand(String scene){
        this.scene = scene;
    }

    @Override
    public void execute() {
        GameModel.getInstance().setGameScene(this.scene);
    }
}
