package com.iss.echoesofdeceit.controller;

import com.iss.echoesofdeceit.controller.command.*;
import com.iss.echoesofdeceit.model.DistrictModel;
import com.iss.echoesofdeceit.model.GameModel;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class IntroductionController {
    @FXML
    private StackPane IntroductionContainer;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnSkip;

    @FXML
    private Label glassLabel;

    private Command nextCommand;
    private Command changeSceneCommand;


    @FXML
    public void initialize(){
        // Esegui il codice dopo che la scena è completamente inizializzata
        Platform.runLater(() -> {
            GameModel.getInstance().setCurrentDistrict("historicCenter");
            GameModel.getInstance().setCurrentZone(1);

            // Inizializzo il DistrictModel
            DistrictModel.getInstance().setDistrict();

            nextCommand = new NextCommand(IntroductionContainer, glassLabel, btnSkip);
            changeSceneCommand = new ChangeSceneCommand("ZonePreview");

            // Collega i pulsanti ai comandi
            btnNext.setOnAction(event -> nextCommand.execute());
            btnSkip.setOnAction(event -> changeSceneCommand.execute());
        });
    }
}
