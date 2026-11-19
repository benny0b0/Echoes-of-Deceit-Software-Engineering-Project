package com.iss.echoesofdeceit.controller.command;

import com.iss.echoesofdeceit.controller.UserFileController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class ContinueGameCommand implements Command{
    private StackPane stackPane;
    public ContinueGameCommand(StackPane stackPane){
        this.stackPane = stackPane;
    }

    @Override
    public void execute() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/iss/echoesofdeceit/view/UserFileChoice.fxml"));
        StackPane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UserFileController controller = loader.getController();
        stackPane.getChildren().add(pane);
        StackPane finalPane = pane;
        controller.getExitContinueGame().setOnAction(actionEvent -> stackPane.getChildren().remove(finalPane));
    }
}
