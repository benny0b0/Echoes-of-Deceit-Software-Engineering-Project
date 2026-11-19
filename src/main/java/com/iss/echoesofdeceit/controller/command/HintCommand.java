package com.iss.echoesofdeceit.controller.command;

import com.iss.echoesofdeceit.controller.HintController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class HintCommand implements Command{
    private StackPane stackPane;
    private String hintText;

    public HintCommand(StackPane stackPane, String hint){
        this.stackPane = stackPane;
        this.hintText = hint;
    }

    @Override
    public void execute() {
        // Carica il file FXML Hint
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/iss/echoesofdeceit/view/Hint.fxml"));
        StackPane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HintController controller = loader.getController();
        this.stackPane.getChildren().add(pane);
        StackPane finalPane = pane;
        controller.getCloseButton().setOnAction(actionEvent -> this.stackPane.getChildren().remove(finalPane));
        controller.getHint().setText(this.hintText);
    }
}
