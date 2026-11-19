package com.iss.echoesofdeceit.controller.command;

import com.iss.echoesofdeceit.controller.SaveFileController;
import com.iss.echoesofdeceit.controller.UserFileController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class UserFileCommand implements Command{
    private StackPane stackPane;
    private String username;

    public UserFileCommand(StackPane stackPane, String username){
        this.stackPane = stackPane;
        this.username = username;
    }

    @Override
    public void execute() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/iss/echoesofdeceit/view/SaveFileChoice.fxml"));
        StackPane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SaveFileController controller = loader.getController();
        controller.setUsername(this.username);
        stackPane.getChildren().add(pane);
        StackPane finalPane = pane;
        controller.getExitSaveChoice().setOnAction(actionEvent -> stackPane.getChildren().remove(finalPane));
    }
}
