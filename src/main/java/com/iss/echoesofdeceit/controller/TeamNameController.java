package com.iss.echoesofdeceit.controller;

import com.iss.echoesofdeceit.controller.command.*;
import javafx.animation.PauseTransition;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class TeamNameController {
    @FXML
    private Label teamNameLabel;
    @FXML
    private Label teamNameLabel2;
    private Command changeSceneCommand;

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            teamNameLabel.setTextAlignment(TextAlignment.CENTER);
            teamNameLabel.setText("Team 03");  // Impostiamo il testo della prima etichetta
            teamNameLabel.setOpacity(0);  // Impostiamo l'iniziale trasparenza a zero

            // Creiamo la transizione fade-in per la prima etichetta
            FadeTransition fadeInTransition1 = new FadeTransition(Duration.seconds(2.6), teamNameLabel);
            fadeInTransition1.setFromValue(0);  // Partiamo da invisibile
            fadeInTransition1.setToValue(1);    // Arriviamo a visibile

            // Iniziamo il fade-in della prima etichetta
            fadeInTransition1.play();

            // Crea una pausa dopo il fade-in della prima etichetta
            PauseTransition pause1 = new PauseTransition(Duration.seconds(2));  // Pausa della durata dell'animazione della prima etichetta
            pause1.setOnFinished(event -> {
                // Dopo la pausa, facciamo partire il fade-in della seconda etichetta
                teamNameLabel2.setText("Presenta");  // Impostiamo il testo della seconda etichetta
                teamNameLabel2.setOpacity(0);  // Impostiamo l'iniziale trasparenza a zero

                // Creiamo la transizione fade-in per la seconda etichetta
                FadeTransition fadeInTransition2 = new FadeTransition(Duration.seconds(2.6), teamNameLabel2);
                fadeInTransition2.setFromValue(0);  // Partiamo da invisibile
                fadeInTransition2.setToValue(1);    // Arriviamo a visibile

                // Iniziamo il fade-in della seconda etichetta
                fadeInTransition2.play();
            });
            pause1.play();

            // Crea una pausa prima del cambiamento di scena (complessiva)
            PauseTransition pause2 = new PauseTransition(Duration.seconds(4.5)); // Pausa di 4 secondi
            pause2.setOnFinished(event -> {
                // Esegui il comando di cambio scena
                changeSceneCommand.execute();
            });
            pause2.play();

            // Comando di cambio scena
            changeSceneCommand = new ChangeSceneCommand("MainMenu");
        });
    }
}
