package com.iss.echoesofdeceit.controller.command;

import com.iss.echoesofdeceit.controller.HintController;
import com.iss.echoesofdeceit.controller.ZoneController;
import com.iss.echoesofdeceit.model.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class ConfirmItemCommand implements Command{
    private ZoneController zoneController;

    public ConfirmItemCommand (ZoneController zoneController){
        this.zoneController = zoneController;
    }

    @Override
    public void execute() {
        // Se deve partire un Puzzle
        if(zoneController.getImageMap().get(zoneController.getImageList().get(zoneController.getCurrentPosition())).getNextTypeOfScene().equals("Puzzle")){
            // Verifica che non sia già stato completato
            if(!PlayerModel.getInstance().getCompletedPuzzles().get(String.valueOf(GameModel.getInstance().getCurrentZone()))){

                // Aggiunta di un eventuale Item all'inventario
                Item item = DistrictModel.getInstance().getZones().get("zone_" + GameModel.getInstance().getCurrentZone()).getExploration().getImagesMap().get(GameModel.getInstance().getCurrentFocusItemBackground()).getItem();
                if(item != null){
                    // Aggiunta alla lista degli item locale
                    PlayerModel.getInstance().getLocalItemList().add(DistrictModel.getInstance().getZones().get("zone_" + GameModel.getInstance().getCurrentZone()).getExploration().getImagesMap().get(GameModel.getInstance().getCurrentFocusItemBackground()).getItem());

                    // Aggiunta alla lista globale
                    PlayerModel.getInstance().getInventory().getItems().add(DistrictModel.getInstance().getZones().get("zone_" + GameModel.getInstance().getCurrentZone()).getExploration().getImagesMap().get(GameModel.getInstance().getCurrentFocusItemBackground()).getItem());
                }
                GameModel.getInstance().setGameScene("Puzzle");

            }
            else {
                // Carica il file FXML Hint
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/iss/echoesofdeceit/view/AlreadyExamined.fxml"));
                StackPane pane = null;
                try {
                    pane = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                HintController controller = loader.getController();
                zoneController.getMainStackPane().getChildren().add(pane);
                StackPane finalPane = pane;
                controller.getCloseButton().setOnAction(actionEvent -> zoneController.getMainStackPane().getChildren().remove(finalPane));
            }
        }
        // Se deve partire un dialogo
        else if(zoneController.getImageMap().get(zoneController.getImageList().get(zoneController.getCurrentPosition())).getNextTypeOfScene().equals("Dialog")){
            // Aggiunta di un eventuale Item all'inventario e stringa nel diario
            Item item = DistrictModel.getInstance().getZones().get("zone_" + GameModel.getInstance().getCurrentZone()).getExploration().getImagesMap().get(GameModel.getInstance().getCurrentFocusItemBackground()).getItem();
            if(item != null && !PlayerModel.getInstance().getLocalItemList().contains(item)){
                // Aggiunta alla lista di item Locale
                PlayerModel.getInstance().getLocalItemList().add(DistrictModel.getInstance().getZones().get("zone_" + GameModel.getInstance().getCurrentZone()).getExploration().getImagesMap().get(GameModel.getInstance().getCurrentFocusItemBackground()).getItem());

                // Aggiunta alla lista globale
                PlayerModel.getInstance().getInventory().getItems().add(DistrictModel.getInstance().getZones().get("zone_" + GameModel.getInstance().getCurrentZone()).getExploration().getImagesMap().get(GameModel.getInstance().getCurrentFocusItemBackground()).getItem());
                // Aggiunta al diario
                PlayerModel.getInstance().getNotebook().setDiary(PlayerModel.getInstance().getNotebook().getDiary()+DistrictModel.getInstance().getZones().get("zone_" + GameModel.getInstance().getCurrentZone()).getExploration().getImagesMap().get(GameModel.getInstance().getCurrentFocusItemBackground()).getDiary());
            }
            GameModel.getInstance().setCurrentDialog(zoneController.getImageMap().get(zoneController.getImageList().get(zoneController.getCurrentPosition())).getCurrent());
            GameModel.getInstance().setGameScene("Dialog");
            GameModel.getInstance().setCurrentDialog(zoneController.getImageMap().get(zoneController.getImageList().get(zoneController.getCurrentPosition())).getCurrent()+1);
        }

    }
}
