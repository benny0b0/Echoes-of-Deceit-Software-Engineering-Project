package com.iss.echoesofdeceit.controller;


import com.iss.echoesofdeceit.model.DistrictModel;
import com.iss.echoesofdeceit.model.GameModel;
import com.iss.echoesofdeceit.model.PuzzleModel;
import com.iss.echoesofdeceit.model.ZoneModel;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConcreteStrategy1 implements Strategy{
    @Override
    public void execute(List<Object> list) throws IOException {

        int currentPuzzle = GameModel.getInstance().getCurrentPuzzle();
        int currentZone = GameModel.getInstance().getCurrentZone();
        int sum = this.getListOfInteger(list).stream().mapToInt(Integer::intValue).sum();
        Map<String, ZoneModel> zones = DistrictModel.getInstance().getZones();
        ZoneModel zone = zones.get("zone_" + currentZone);
        PuzzleModel puzzle = zone.getPuzzle().get(currentPuzzle);
        if(sum == Integer.parseInt(puzzle.getSolution())) {
            PuzzleController.list.clear();
            SolutionController.setIsSolved(true);
            GameModel.getInstance().setGameScene("Solution");
        }
        else{
            SolutionController.setIsSolved(false);
            GameModel.getInstance().setGameScene("Solution");
        }
    }

    private List<Integer> getListOfInteger(List<Object> objectList){
        List<Integer> integerList = new ArrayList<>();
        for (Object obj : objectList) {

            if (obj instanceof String) {
                integerList.add(Integer.parseInt((String) obj));
            }
        }
        return integerList;
    }
}
