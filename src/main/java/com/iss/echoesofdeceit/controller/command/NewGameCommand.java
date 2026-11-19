package com.iss.echoesofdeceit.controller.command;

import com.iss.echoesofdeceit.controller.NewGameController;
import com.iss.echoesofdeceit.model.GameModel;
import com.iss.echoesofdeceit.model.SaveDataManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class NewGameCommand implements Command {
    private StackPane stackPane;
    public NewGameCommand(StackPane stackPane){
        this.stackPane = stackPane;
    }

    @Override
    public void execute() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/iss/echoesofdeceit/view/NewGame.fxml"));
        StackPane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        NewGameController controller = loader.getController();
        stackPane.getChildren().add(pane);
        StackPane finalPane = pane;
        controller.getExitNewGameButton().setOnAction(actionEvent -> stackPane.getChildren().remove(finalPane));
    }


}
