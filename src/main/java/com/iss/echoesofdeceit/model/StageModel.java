package com.iss.echoesofdeceit.model;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class StageModel {
    private final Stage stage;  // Riferimento alla finestra principale
    private String currentScene;
    private static StageModel instance;

    private StageModel(Stage stage){
        this.stage = stage;
    }

    public static StageModel getInstance(Stage stage) {
        if(instance == null) {
            instance = new StageModel(stage);
        }
        return instance;
    }

    // Metodo per cambiare scena con effetto di transizione fade
    public void setCurrentScene(String sceneName) {
        try {
            // Ottieni la risoluzione effettiva dello schermo, escluso lo spazio per il dock
            Screen screen = Screen.getPrimary();
            double screenWidth = screen.getVisualBounds().getWidth();
            double screenHeight = screen.getVisualBounds().getHeight();

            // Carica la nuova scena
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/iss/echoesofdeceit/view/" + sceneName + ".fxml")));
            Scene newScene = new Scene(root, screenWidth, screenHeight, Color.BLACK);

            // Crea una scena invisibile (con opacità 0)
            newScene.getRoot().setOpacity(0);

            // Se esiste già una scena, applica la transizione fade out
            if (stage.getScene() != null) {
                FadeTransition fadeOut = new FadeTransition(Duration.millis(200), stage.getScene().getRoot());
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);

                fadeOut.setOnFinished(event -> {
                    // Una volta finita la transizione fade, cambia la scena
                    stage.setMaximized(true);
                    stage.setScene(newScene);

                    // Crea una transizione fade per la nuova scena
                    FadeTransition fadeIn = new FadeTransition(Duration.millis(200), newScene.getRoot());
                    fadeIn.setFromValue(0.0);
                    fadeIn.setToValue(1.0);
                    fadeIn.play();
                });

                // Avvia la transizione fade out
                fadeOut.play();
            } else {
                // Se non c'è una scena, cambia direttamente la scena senza transizione fade
                stage.setScene(newScene);

                // Esegui la transizione fade in subito
                FadeTransition fadeIn = new FadeTransition(Duration.millis(200), newScene.getRoot());
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            }

            this.currentScene = sceneName;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCurrentScene() {
        return currentScene;
    }

    public Stage getStage() {
        return stage;
    }
}