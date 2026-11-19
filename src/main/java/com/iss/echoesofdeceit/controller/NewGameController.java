package com.iss.echoesofdeceit.controller;

import com.iss.echoesofdeceit.controller.command.Command;
import com.iss.echoesofdeceit.controller.command.StartNewGameCommand;
import com.iss.echoesofdeceit.model.SaveDataManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

public class NewGameController {

    @FXML
    private TextField nameField;
    @FXML
    private Button startButton;
    @FXML
    private StackPane mainStackPane;
    @FXML
    private Button exitNewGameButton;
    @FXML
    private Label errorLabel;

    private SaveDataManager playerModel;

    private Command startNewGameCommand;


    @FXML
    private void initialize() {
        Platform.runLater(()->{
            startNewGameCommand = new StartNewGameCommand(mainStackPane, nameField, errorLabel);
            nameField.requestFocus();
            nameField.setOnMouseClicked(e -> errorLabel.setText(""));
            nameField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 15) {
                    nameField.setText(oldValue);
                }
            });
            startButton.setOnAction(e -> startNewGameCommand.execute());
        });
    }

    public Button getExitNewGameButton() {
        return exitNewGameButton;
    }
}


