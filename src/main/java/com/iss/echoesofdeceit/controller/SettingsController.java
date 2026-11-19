package com.iss.echoesofdeceit.controller;

import com.iss.echoesofdeceit.model.MusicsManager;
import com.iss.echoesofdeceit.model.SoundsManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SettingsController {
    @FXML
    private Button btnExitSettings;
    @FXML
    private Button btnDecrementMusic;
    @FXML
    private Label musicVolumeLabel;
    @FXML
    private Button btnInrementMusic;
    @FXML
    private Button btnDecrementSound;
    @FXML
    private Button btnInrementSound;
    @FXML
    private Label soundMusicLabel;

    public void initialize(){
        musicVolumeLabel.setText(String.valueOf((int) (MusicsManager.getInstance().getVolume() * 100)));
        soundMusicLabel.setText(String.valueOf((int) (SoundsManager.getInstance().getVolume() * 100)));
        Platform.runLater(()->{
            btnDecrementMusic.setOnAction(e->{
                MusicsManager.getInstance().decrementVolume();
                musicVolumeLabel.setText(String.valueOf((int) (MusicsManager.getInstance().getVolume() * 100)));
            });

            btnInrementMusic.setOnAction( e->{
                MusicsManager.getInstance().incrementVolume();
                musicVolumeLabel.setText(String.valueOf((int) (MusicsManager.getInstance().getVolume() * 100)));
            });

            btnDecrementSound.setOnAction(e->{
                SoundsManager.getInstance().decrementVolume();
                soundMusicLabel.setText(String.valueOf((int) (SoundsManager.getInstance().getVolume() * 100)));
            });

            btnInrementSound.setOnAction(e->{
                SoundsManager.getInstance().incrementVolume();
                soundMusicLabel.setText(String.valueOf((int) (SoundsManager.getInstance().getVolume() * 100)));
            });
        });
    }

    public Button getBtnExitSettings() {
        return btnExitSettings;
    }
}
