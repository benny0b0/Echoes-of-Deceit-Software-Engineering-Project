package com.iss.echoesofdeceit.controller;

import com.iss.echoesofdeceit.controller.command.Command;
import com.iss.echoesofdeceit.controller.command.NextDialogCommand;
import com.iss.echoesofdeceit.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class DialogController {
    @FXML
    private Label name;
    @FXML
    private Label conversation;
    @FXML
    private Button next;
    @FXML
    private ImageView oneContainer;
    @FXML
    private ImageView twoContainer;
    @FXML
    private StackPane mainStackPane;

    private Command nextDialogCommand;
    private DialogModel dialog;


    public DialogController() throws IOException {
        DistrictModel districtModel = DistrictModel.getInstance();
        Map<String, ZoneModel> zones = districtModel.getZones();
        ZoneModel zone = zones.get("zone_" + GameModel.getInstance().getCurrentZone());
        this.dialog=zone.getDialogs().get(GameModel.getInstance().getCurrentDialog());
    }

    public void initialize(){
        Platform.runLater(()->{
            nextDialogCommand = new NextDialogCommand(dialog, oneContainer, twoContainer, name, conversation);
            String bgPath = String.valueOf(Objects.requireNonNull(getClass().getResource("/com/iss/echoesofdeceit/images/" + GameModel.getInstance().getCurrentDistrict()+GameModel.getInstance().getCurrentZone()+"_dialog.jpg")));
            if (PlayerModel.getInstance().getCurrentDistrict().get("DistrictName").equals("suburbsDistrict") && PlayerModel.getInstance().getCurrentDistrict().get("ZoneName").equals("3"))
                PlayerModel.getInstance().setCompletedPuzzles(Map.of("1", true, "2", true, "3", true));
            mainStackPane.setStyle("-fx-background-image: url('" + bgPath + "');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat;" +
                    "-fx-background-size: 100% 100%;");
            oneContainer.setImage(new Image(String.valueOf(Objects.requireNonNull(getClass().getResource("/com/iss/echoesofdeceit/images/" +dialog.getInvestigator())))));
            twoContainer.setImage(new Image(String.valueOf(Objects.requireNonNull(getClass().getResource("/com/iss/echoesofdeceit/images/" +dialog.getInterlocutor())))));
            name.setText(dialog.getConversation().get(0).getSpeaker());
            nextDialogCommand.execute();
            next.setOnAction(event -> nextDialogCommand.execute());
        });
    }

}
