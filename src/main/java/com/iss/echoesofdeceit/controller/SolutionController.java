package com.iss.echoesofdeceit.controller;
import com.iss.echoesofdeceit.controller.command.ChangeSceneCommand;
import com.iss.echoesofdeceit.controller.command.Command;
import com.iss.echoesofdeceit.controller.command.ResetPuzzleCommand;
import com.iss.echoesofdeceit.model.DistrictModel;
import com.iss.echoesofdeceit.model.GameModel;
import com.iss.echoesofdeceit.model.PlayerModel;
import com.iss.echoesofdeceit.model.SaveDataManager;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.io.IOException;

public class SolutionController {
    @FXML
    private Label textLabel;
    @FXML
    private Label resultLabel;
    @FXML
    private Button button;

    private Command resetCommand;
    private Command changeScene;
    private String textToType = "La soluzione e'";
    private String textToType2 = "...";
    private static boolean isSolved;

    public static boolean isIsSolved() {
        return isSolved;
    }

    public static void setIsSolved(boolean isSolved) {
        SolutionController.isSolved = isSolved;
    }

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            resetCommand = new ResetPuzzleCommand();
            if(PlayerModel.getInstance().getCurrentDistrict().get("DistrictName").equals("nobleDistrict") && PlayerModel.getInstance().getCurrentDistrict().get("ZoneName").equals("3"))
                changeScene = new ChangeSceneCommand("Ending");
            else
                changeScene = new ChangeSceneCommand(DistrictModel.getInstance().getZones().get("zone_"+GameModel.getInstance().getCurrentZone()).getExploration().getImagesMap().get(GameModel.getInstance().getCurrentFocusItemBackground()).getNextNextScene());

            // Timeline per il primo testo
            Timeline timeline1 = new Timeline();
            final int[] index1 = {0};  // Indice per tracciare la posizione del carattere da aggiungere per il primo testo
            KeyFrame keyFrame1 = new KeyFrame(Duration.millis(150), event -> {
                if (index1[0] < textToType.length()) {
                    textLabel.setText(textToType.substring(0, index1[0] + 1));
                    index1[0]++;
                }
            });

            timeline1.getKeyFrames().add(keyFrame1);
            timeline1.setCycleCount(Timeline.INDEFINITE);  // Ripete indefinitamente
            timeline1.play();

            // Crea una transizione con durata di 4 secondi prima di avviare la seconda animazione
            PauseTransition pauseAfterFirstText = new PauseTransition(Duration.seconds(2));
            pauseAfterFirstText.setOnFinished(event -> {
                // Dopo la pausa, iniziamo la seconda Timeline per il secondo testo
                Timeline timeline2 = new Timeline();
                final int[] index2 = {0};  // Indice per tracciare la posizione del carattere da aggiungere per il secondo testo
                KeyFrame keyFrame2 = new KeyFrame(Duration.millis(800), event2 -> {
                    if (index2[0] < textToType2.length()) {
                        textLabel.setText(textToType + textToType2.substring(0, index2[0] + 1));  // Combina il primo e il secondo testo
                        index2[0]++;
                    }
                });

                timeline2.getKeyFrames().add(keyFrame2);
                timeline2.setCycleCount(Timeline.INDEFINITE);  // Ripete indefinitamente
                timeline2.play();
            });
            pauseAfterFirstText.play();

            // Aggiungi il fade-in per resultLabel dopo 5 secondi
            PauseTransition pauseAfterText = new PauseTransition(Duration.seconds(5));
            pauseAfterText.setOnFinished(event -> {
                // Imposta l'opacità iniziale del resultLabel a 0 (invisibile)
                resultLabel.setOpacity(0);
                button.setOpacity(0);  // Nascondi anche il bottone inizialmente

                // Modifica del testo e colore del resultLabel in base alla risposta
                if (isSolved) {
                    resultLabel.setStyle("-fx-text-fill: green;");
                    resultLabel.setText("CORRETTA");
                    button.setText("Continua");
                    button.setOnAction(e->changeScene.execute());
                    PlayerModel.getInstance().getCompletedPuzzles().replace(String.valueOf(GameModel.getInstance().getCurrentZone()), true);
                    PlayerModel.getInstance().getCurrentDistrict().replace("DistrictName", GameModel.getInstance().getCurrentDistrict());
                    PlayerModel.getInstance().getCurrentDistrict().replace("ZoneName", String.valueOf(GameModel.getInstance().getCurrentZone()));
                    PlayerModel.getInstance().getNotebook().setDiary(PlayerModel.getInstance().getNotebook().getDiary()+DistrictModel.getInstance().getZones().get("zone_" + GameModel.getInstance().getCurrentZone()).getExploration().getImagesMap().get(GameModel.getInstance().getCurrentFocusItemBackground()).getDiary());


                    // Salvataggio dati utente
                    if (!(PlayerModel.getInstance().getCurrentDistrict().get("DistrictName").equals("nobleDistrict") && PlayerModel.getInstance().getCurrentDistrict().get("ZoneName").equals("3"))) {
                        try {
                            SaveDataManager.saveUserData(PlayerModel.getInstance());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }


                } else {
                    resultLabel.setStyle("-fx-text-fill: red;");
                    resultLabel.setText("SBAGLIATA");
                    button.setText("Riprova");
                    button.setOnAction(e -> resetCommand.execute());
                }

                // Aggiungi un'animazione di fade-in per il resultLabel
                FadeTransition fadeInResult = new FadeTransition(Duration.seconds(2), resultLabel);
                fadeInResult.setFromValue(0);  // Inizia da invisibile
                fadeInResult.setToValue(1);    // Arriva a visibile
                fadeInResult.play();           // Avvia l'animazione di fade-in per resultLabel

                // Aggiungi una pausa di 1 secondo prima di fare apparire il bottone
                PauseTransition pauseBeforeButton = new PauseTransition(Duration.seconds(1));
                pauseBeforeButton.setOnFinished(e -> {
                    // Rendi il bottone visibile prima di animarlo
                    button.setVisible(true);  // Rendi il bottone visibile prima dell'animazione
                    // Crea un'animazione di fade-in per il button
                    FadeTransition fadeInButton = new FadeTransition(Duration.seconds(2), button);
                    fadeInButton.setFromValue(0);  // Inizia da invisibile
                    fadeInButton.setToValue(1);    // Arriva a visibile
                    fadeInButton.play();           // Avvia l'animazione di fade-in per il bottone
                });
                pauseBeforeButton.play();  // Avvia la pausa prima di far apparire il bottone
            });
            pauseAfterText.play();
        });
    }

}
