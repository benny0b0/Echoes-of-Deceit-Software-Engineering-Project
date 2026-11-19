package com.iss.echoesofdeceit.controller.command;

import com.iss.echoesofdeceit.model.GameModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangeSceneCommandTest {

    @BeforeEach
    void resetGameModel() {
        GameModel.getInstance().setGameScene("defaultScene");
    }

    @Test
    void execute_ShouldChangeScene() {

        String newScene = "newScene";
        ChangeSceneCommand changeSceneCommand = new ChangeSceneCommand(newScene);

        changeSceneCommand.execute();


        assertEquals(newScene, GameModel.getInstance().getGameScene(), "La scena del gioco non è stata aggiornata correttamente.");
    }
}
