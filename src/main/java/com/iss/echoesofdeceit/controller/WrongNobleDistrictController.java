package com.iss.echoesofdeceit.controller;

import com.iss.echoesofdeceit.controller.command.Command;
import com.iss.echoesofdeceit.controller.command.LoadSaveFileCommand;
import com.iss.echoesofdeceit.model.MusicsManager;
import com.iss.echoesofdeceit.model.PlayerModel;
import com.iss.echoesofdeceit.model.SaveDataManager;
import com.iss.echoesofdeceit.model.SoundsManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.util.Duration;

import java.util.List;

public class WrongNobleDistrictController {
    @FXML
    private Button loadCheckPoint;

    // Comandi
    private Command loadSaveFileCommand;


    public void initialize(){
        Platform.runLater(()->{
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(1.5), e -> {
                        // Esegui il suono dopo 4 secondi
                        SoundsManager.getInstance().playSound("horn");
                    })
            );
            timeline.setCycleCount(1);
            timeline.play();

            List<String> files = SaveDataManager.getSaveFilesForUser(PlayerModel.getInstance().getName());
            String checkPoint = files.stream()
                    .filter(file -> file.contains("academiesDistrict3")) // Condizione per trovare la stringa desiderata
                    .findFirst()
                    .orElse(null);
            loadSaveFileCommand = new LoadSaveFileCommand(PlayerModel.getInstance().getName(), checkPoint);
            loadCheckPoint.setOnAction(e ->{
                loadSaveFileCommand.execute();
                MusicsManager.getInstance().playMusic("Zone");
            });
        });

    }
}
