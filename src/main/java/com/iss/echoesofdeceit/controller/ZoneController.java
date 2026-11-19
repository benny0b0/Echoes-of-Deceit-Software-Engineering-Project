package com.iss.echoesofdeceit.controller;

import com.iss.echoesofdeceit.controller.command.*;
import com.iss.echoesofdeceit.model.DistrictModel;
import com.iss.echoesofdeceit.model.ExplorationImage;
import com.iss.echoesofdeceit.model.GameModel;
import com.iss.echoesofdeceit.model.PlayerModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class ZoneController {
    @FXML
    private StackPane mainStackPane;
    @FXML
    private StackPane focusImagesContainer;
    @FXML
    private Button pauseButton;
    @FXML
    private Button confirmButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button backButton;

    // Comandi
    private Command pauseCommand;
    private Command nextItemCommand;
    private Command backItemCommand;
    private Command confirmItemCommand;

    // Attributi
    private int currentPosition;
    private List<String> imageList; //Lista di immagini
    private Map<String, ExplorationImage> imageMap;

    public void initialize() throws IOException {
        // Creazione dei comandi
        pauseCommand = new PauseCommand(mainStackPane);
        nextItemCommand = new NextItemCommand(this);
        backItemCommand = new BackItemCommand(this);
        confirmItemCommand = new ConfirmItemCommand(this);

        imageMap = DistrictModel.getInstance().getZones().get("zone_" + GameModel.getInstance().getCurrentZone()).getExploration().getImagesMap();
        imageList = DistrictModel.getInstance().getZones().get("zone_" + GameModel.getInstance().getCurrentZone()).getExploration().getImageList();
        currentPosition = 0;

        // Esecuzione dei comandi
        Platform.runLater(()->{
            loadCurrentBackground();
            pauseButton.setOnAction(e -> pauseCommand.execute());
            nextButton.setOnAction(e -> nextItemCommand.execute());
            backButton.setOnAction(e -> backItemCommand.execute());
            confirmButton.setOnAction(e -> confirmItemCommand.execute());
        });
    }

    // Carica il background corrente
    public void loadCurrentBackground() {
        // Se la zona è completata && è anche l'ultima zona
        if(this.isZoneCompleted() && GameModel.getInstance().getCurrentZone() == DistrictModel.getInstance().getZones().size()){
            String background = DistrictModel.getInstance().getZones().get("zone_" + GameModel.getInstance().getCurrentZone()).getExploration().getImageList().get(currentPosition);
            GameModel.getInstance().setCurrentFocusItemBackground(background);
            String bgPath = Objects.requireNonNull(getClass().getResource("/com/iss/echoesofdeceit/images/" + background)).toExternalForm();
            focusImagesContainer.setStyle("-fx-background-image: url('" + bgPath + "');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat;" +
                    "-fx-background-size: 100% 100%;");


            ZonePreviewController.isDistrictCompleted = true;
            GameModel.getInstance().setGameScene("ZonePreview");
        }
        // Verifico se ho completato solo la zona corrente
        else if (this.isZoneCompleted()){
            String background = DistrictModel.getInstance().getZones().get("zone_" + GameModel.getInstance().getCurrentZone()).getExploration().getImageList().get(currentPosition);
            GameModel.getInstance().setCurrentFocusItemBackground(background);
            String bgPath = Objects.requireNonNull(getClass().getResource("/com/iss/echoesofdeceit/images/" + background)).toExternalForm();
            focusImagesContainer.setStyle("-fx-background-image: url('" + bgPath + "');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat;" +
                    "-fx-background-size: 100% 100%;");

            // Incremento la zona sia nel GameModel che nel PlayerModel
            GameModel.getInstance().setCurrentZone(GameModel.getInstance().getCurrentZone() + 1);

            PlayerModel.getInstance().getCurrentDistrict().replace("ZoneName", String.valueOf(GameModel.getInstance().getCurrentZone()));

            // Svuoto la lista di items locale
            PlayerModel.getInstance().getLocalItemList().clear();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/iss/echoesofdeceit/view/ChangeZone.fxml"));
            StackPane pane = null;
            try {
                pane = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ChangeZoneController controller = loader.getController();
            mainStackPane.getChildren().add(pane);
            StackPane finalPane = pane;
            controller.getChangeZoneButton().setOnAction(actionEvent -> GameModel.getInstance().setGameScene("ZonePreview"));

        }
        else {
            String background = DistrictModel.getInstance().getZones().get("zone_" + GameModel.getInstance().getCurrentZone()).getExploration().getImageList().get(currentPosition);
            GameModel.getInstance().setCurrentFocusItemBackground(background);
            String bgPath = Objects.requireNonNull(getClass().getResource("/com/iss/echoesofdeceit/images/" + background)).toExternalForm();
            focusImagesContainer.setStyle("-fx-background-image: url('" + bgPath + "');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat;" +
                    "-fx-background-size: 100% 100%;");
//                    "-fx-background-size: cover;");
        }
    }



    // Verifico se ho completato la zona
    private boolean isZoneCompleted(){
        // Verifico se il numero degli items raccolti sono uguali al numero di items disponibili nella zona
        // e se tutti i puzzle sono stati completati
        if((PlayerModel.getInstance().getCompletedPuzzles().get(String.valueOf(GameModel.getInstance().getCurrentZone()))) && (PlayerModel.getInstance().getLocalItemList().size() == DistrictModel.getInstance().getZones().get("zone_" + GameModel.getInstance().getCurrentZone()).getNumberOfItems()))
            return true;
        else
            return false;
    }


    // Getter e Setter
    public int getCurrentPosition(){
        return this.currentPosition;
    }
    public void setCurrentPosition(int x){
        this.currentPosition = x;
    }

    public List<String> getImageList() {
        return this.imageList;
    }

    public Map<String, ExplorationImage> getImageMap() {
        return this.imageMap;
    }

    public StackPane getMainStackPane(){
        return this.mainStackPane;
    }

}
