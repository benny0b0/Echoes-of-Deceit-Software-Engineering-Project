package com.iss.echoesofdeceit.controller.command;

import com.iss.echoesofdeceit.controller.PauseMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class PauseCommand implements Command{
    private StackPane stackPane;

    public PauseCommand(StackPane stackPane){
        this.stackPane = stackPane;
    }

    @Override
    public void execute() {
        // Carica il file FXML PauseMenu
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/iss/echoesofdeceit/view/PauseMenu.fxml"));
        StackPane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PauseMenuController controller = loader.getController();
        stackPane.getChildren().add(pane);
        StackPane finalPane = pane;
        controller.getBtnExitPause().setOnAction(actionEvent -> stackPane.getChildren().remove(finalPane));
    }

    public StackPane getStackPane() {
        return this.stackPane;
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }
}
