package com.iss.echoesofdeceit.controller;

import com.iss.echoesofdeceit.controller.command.*;
import com.iss.echoesofdeceit.model.DistrictModel;
import com.iss.echoesofdeceit.model.GameModel;
import com.iss.echoesofdeceit.model.PlayerModel;
import com.iss.echoesofdeceit.model.SaveDataManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import java.io.IOException;
import java.util.List;
import java.util.Objects;


public class ZonePreviewController {
    @FXML
    private StackPane mainStackPane;
    @FXML
    private Label zoneName;
    @FXML
    private Button pauseButton;
    @FXML
    private Button beginInvestigation;


    // Comandi
    private Command pauseCommand;
    private Command changeSceneCommand;
    private Command nextDistrictCommand;
    private Command loadSaveFileCommand;

    // Attributi
    public static boolean isDistrictCompleted = false;

    public void initialize() throws IOException {
        // Carico il background  e il nome di zona corrente
        loadCurrentBackground();
        loadZoneName();

        // Creazione dei comandi
        changeSceneCommand = new ChangeSceneCommand("Zone");
        pauseCommand = new PauseCommand(mainStackPane);
        nextDistrictCommand = new NextDistrictCommand(this.mainStackPane);

        // Esecuzione dei comandi
        Platform.runLater(()->{
            List<String> files = SaveDataManager.getSaveFilesForUser(PlayerModel.getInstance().getName());
            String historicCenter = files.stream()
                    .filter(file -> file.contains("historicCenter3")) // Condizione per trovare la stringa desiderata
                    .findFirst()
                    .orElse(null);

            loadSaveFileCommand = new LoadSaveFileCommand(PlayerModel.getInstance().getName(),historicCenter);
            pauseButton.setOnAction(e -> pauseCommand.execute());
            if(ZonePreviewController.isDistrictCompleted){
                if (PlayerModel.getInstance().getCurrentDistrict().get("DistrictName").equals("suburbsDistrict") && PlayerModel.getInstance().getCurrentDistrict().get("ZoneName").equals("3")) {
                    beginInvestigation.setText("Carica l'ultimo punto di controllo");
                    beginInvestigation.setOnAction(e -> {
                        try {
                            for(String fileName : files)
                                if(fileName.contains("suburbsDistrict"))
                                    SaveDataManager.deleteSaveFile(PlayerModel.getInstance().getName(), fileName);

                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        loadSaveFileCommand.execute();
                    });
                }
                else {
                    beginInvestigation.setText("Cambia quartiere");
                    beginInvestigation.setOnAction(e -> nextDistrictCommand.execute());
                }

            }
            else {
                beginInvestigation.setOnAction(e -> changeSceneCommand.execute());
            }
        });
    }

    public void loadCurrentBackground() {
        // Carica un'immagine nella stringa bgPath
        String bgPath = Objects.requireNonNull(getClass().getResource(
                "/com/iss/echoesofdeceit/images/" +
                        GameModel.getInstance().getCurrentDistrict() +
                        GameModel.getInstance().getCurrentZone() + ".jpg"
        )).toExternalForm();
        mainStackPane.setStyle("-fx-background-image: url('" + bgPath + "');" +
                "-fx-background-position: center center;" +
                "-fx-background-repeat: no-repeat;" +
//                "-fx-background-size: cover;");
                "-fx-background-size: 100% 100%;");
    }
    public void loadZoneName(){
        zoneName.setText(DistrictModel.getInstance().getZones().get("zone_" + GameModel.getInstance().getCurrentZone()).getExploration().getRoomName());
    }

}
