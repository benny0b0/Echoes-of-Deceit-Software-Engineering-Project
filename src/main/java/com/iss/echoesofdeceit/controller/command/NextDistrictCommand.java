package com.iss.echoesofdeceit.controller.command;

import com.iss.echoesofdeceit.controller.HintController;
import com.iss.echoesofdeceit.controller.NextDistrictController;
import com.iss.echoesofdeceit.model.GameModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class NextDistrictCommand implements Command{
    private StackPane mainStackPane;

    public NextDistrictCommand(StackPane pane){
        this.mainStackPane = pane;
    }

    @Override
    public void execute() {
        // Carica il file FXML Hint
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/iss/echoesofdeceit/view/NextDistrict.fxml"));
        StackPane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        NextDistrictController controller = loader.getController();
        this.mainStackPane.getChildren().add(pane);
        StackPane finalPane = pane;
        controller.getBackToGameButton().setOnAction(actionEvent -> this.mainStackPane.getChildren().remove(finalPane));
    }
}
