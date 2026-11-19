package com.iss.echoesofdeceit.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class HintController {
    @FXML
    private Label hint;
    @FXML
    private Button closeButton;

    public Button getCloseButton() {
        return closeButton;
    }

    public Label getHint() {
        return hint;
    }

}
