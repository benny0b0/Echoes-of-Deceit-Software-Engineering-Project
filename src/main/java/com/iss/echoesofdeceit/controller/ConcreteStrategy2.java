package com.iss.echoesofdeceit.controller;

import com.iss.echoesofdeceit.model.*;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConcreteStrategy2 implements Strategy {
    @Override
    public void execute(List<Object> list) throws IOException {
        int currentPuzzle = GameModel.getInstance().getCurrentPuzzle();
        int currentZone = GameModel.getInstance().getCurrentZone();
        String solution;

        // Se siamo al quartiere popolare zona 2 (Puzzle del pianoforte)
        if(GameModel.getInstance().getCurrentDistrict().equals("popularDistrict") && currentZone == 2){
            // Creo una sottolista con solo gli ultimi 6 tasti premuti
            List<String> listPiano = this.getListOfStrings(list);
            if(listPiano.size() >= 6)
                listPiano = this.getListOfStrings(list).subList(this.getListOfStrings(list).size() - 6, this.getListOfStrings(list).size());
            solution = String.join("", listPiano); // Concateno le stringhe ottenute
        }
        else
            solution = String.join("", this.getListOfStrings(list)); // Concateno le stringhe ottenute


        Map<String, ZoneModel> zones = DistrictModel.getInstance().getZones();
        ZoneModel zone = zones.get("zone_" + currentZone);
        PuzzleModel puzzle = zone.getPuzzle().get(currentPuzzle);
        if(solution.toLowerCase().trim().replaceAll("\\s+", "").equals(puzzle.getSolution())) {
            PuzzleController.list.clear();
            SolutionController.setIsSolved(true);

            // Segno come completato il puzzle della zona corrente all'interno del PlayerModel
            PlayerModel.getInstance().getCompletedPuzzles().put(String.valueOf(GameModel.getInstance().getCurrentZone()), true);

            GameModel.getInstance().setGameScene("Solution");
        }
        else{
            SolutionController.setIsSolved(false);
            GameModel.getInstance().setGameScene("Solution");
        }

    }

    private List<String> getListOfStrings(List<Object> objectList){
        List<String> stringList = new ArrayList<>();
        for (Object obj : objectList) {
            if (obj instanceof String) {
                stringList.add((String) obj);
            }
            if(obj instanceof Label){
                stringList.add((String) ((Label) obj).getText());
            }
        }
        return stringList;
    }
}
