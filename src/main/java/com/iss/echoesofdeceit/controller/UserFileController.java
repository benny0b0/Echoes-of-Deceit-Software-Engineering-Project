package com.iss.echoesofdeceit.controller;

import com.iss.echoesofdeceit.controller.command.Command;
import com.iss.echoesofdeceit.controller.command.UserFileCommand;
import com.iss.echoesofdeceit.model.SaveDataManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.util.List;

public class UserFileController {
    @FXML
    private StackPane mainStackPane;
    @FXML
    private Button exitContinueGame;

    @FXML
    private Button continueButton1;

    @FXML
    private Button continueButton2;

    @FXML
    private Button continueButton3;

    private Command userFileCommand;

    @FXML
    public void initialize() {
        Platform.runLater(()->{
            List<String> savedUsers = SaveDataManager.getSavedUsers();
            setupUserButtons(savedUsers);
        });

    }

    public Button getExitContinueGame() {
        return exitContinueGame;
    }

    private void setupUserButtons(List<String> savedUsers) {
        Button[] buttons = {continueButton1, continueButton2, continueButton3};

        for (int i = 0; i < Math.min(buttons.length, savedUsers.size()); i++) {
            final String user = savedUsers.get(i);

            buttons[i].setText(user);
            buttons[i].setDisable(false);

            buttons[i].setOnAction(e -> {
                userFileCommand = new UserFileCommand(mainStackPane, user);
                userFileCommand.execute();
            });
        }
    }

}
