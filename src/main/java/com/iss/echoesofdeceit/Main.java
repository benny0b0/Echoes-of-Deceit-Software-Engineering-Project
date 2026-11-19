package com.iss.echoesofdeceit;

import com.iss.echoesofdeceit.controller.MusicsController;
import com.iss.echoesofdeceit.controller.StageController;
import com.iss.echoesofdeceit.model.*;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameModel gameModel = GameModel.getInstance();

        StageModel stageModel = StageModel.getInstance(primaryStage);
        MusicsManager musicsManager = MusicsManager.getInstance();
        SoundsManager soundsManager = SoundsManager.getInstance();
        StageController sceneController = new StageController(gameModel, stageModel);
        MusicsController musicsController = new MusicsController(gameModel, musicsManager);

        gameModel.setGameScene("TeamName");
        primaryStage.setTitle("Echoes of Deceit");
        Image icon = new Image(Objects.requireNonNull(getClass().getResource("images/logo.png")).toExternalForm());
        primaryStage.getIcons().add(icon);

        // Ottenere la risoluzione dello schermo
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Impostare la finestra con una dimensione massima che tiene conto dei dock
        primaryStage.setWidth(screenBounds.getWidth());
        primaryStage.setHeight(screenBounds.getHeight());

        // Imposta la finestra massimizzata
        primaryStage.setMaximized(true);
        primaryStage.setResizable(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();  // Avvia l'applicazione JavaFX
    }
}
