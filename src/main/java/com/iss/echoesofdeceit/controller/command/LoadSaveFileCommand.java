package com.iss.echoesofdeceit.controller.command;

import com.iss.echoesofdeceit.controller.PuzzleElement;
import com.iss.echoesofdeceit.model.*;
import javafx.application.Platform;

import java.io.IOException;

public class LoadSaveFileCommand implements Command {
    private String userName;
    private String saveFile;

    public LoadSaveFileCommand(String userName, String saveFile) {
        this.userName = userName;
        this.saveFile = saveFile;
    }

    @Override
    public void execute() {
        // Aggiungi log per capire se entra nel metodo
        try {
            SaveDataManager.loadPlayer(saveFile+".json", userName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Inizializza variabili
        PuzzleElement.setIndex(1);
        PuzzleElement.setTextAreaString("");
        GameModel.getInstance().setCurrentDistrict(PlayerModel.getInstance().getCurrentDistrict().get("DistrictName"));
        GameModel.getInstance().setCurrentZone(Integer.parseInt(PlayerModel.getInstance().getCurrentDistrict().get("ZoneName")));

        DistrictModel.getInstance().setDistrict(); // Aggiorno il districtModel con i dati corretti

        GameModel.getInstance().setGameScene("ZonePreview");

    }
}