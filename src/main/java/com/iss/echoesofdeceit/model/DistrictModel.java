package com.iss.echoesofdeceit.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.util.Map;


public class DistrictModel {
    @JsonProperty("zones")
    private Map<String, ZoneModel> zones;
    private static DistrictModel instance;

    public static DistrictModel getInstance(){
        if(instance==null){
            String fileName = GameModel.getInstance().getCurrentDistrict() + ".json"; // Percorso del file JSON
            try {
                instance = GameDataManager.loadQuartiere(fileName, GameModel.getInstance().getCurrentDistrict());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    // Getter e Setter
    public Map<String, ZoneModel> getZones() {
        return zones;
    }
    public void setZones(Map<String, ZoneModel> zones) {
        this.zones = zones;
    }

    public void setDistrict(){
        String fileName = GameModel.getInstance().getCurrentDistrict() + ".json"; // Percorso del file JSON
        try {
            instance = GameDataManager.loadQuartiere(fileName, GameModel.getInstance().getCurrentDistrict());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}