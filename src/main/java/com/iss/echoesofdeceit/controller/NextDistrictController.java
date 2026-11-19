package com.iss.echoesofdeceit.controller;

import com.iss.echoesofdeceit.model.DistrictModel;
import com.iss.echoesofdeceit.model.GameModel;
import com.iss.echoesofdeceit.model.PlayerModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Objects;

public class NextDistrictController {
    @FXML
    private StackPane mainStackPane;
    @FXML
    private HBox hboxOfDistricts;
    @FXML
    private Button backToGameButton;
    @FXML
    private Button backToMenuButton;

    public void initialize(){
        backToMenuButton.setOnAction(e -> GameModel.getInstance().setGameScene("MainMenu"));

        // In base al quartiere corrente, mostra la lista dei quartieri disponibili
        switch (GameModel.getInstance().getCurrentDistrict()){
            case "historicCenter":
                this.addElement("popularDistrict1_dialog", "Quartiere popolare");
                this.addElement("suburbsDistrict1_dialog", "Sobborghi");
                break;
            case "popularDistrict":
                this.addElement("academiesDistrict1_dialog", "Accademie");
                break;
            case "academiesDistrict":
                this.addElement("marketDistrict1_dialog", "Mercato");
                this.addElement("MainMenu", "Quartiere Nobiliare");
                break;
            case "marketDistrict":
                this.addElement("nobleDistrict1_dialog", "Quartiere nobiliare");
                break;
        }

    }

    private void addElement(String imageName, String districtName){
        Button saveFileButton = new Button();
        saveFileButton.getStyleClass().add("save-file-button");

        // Crea uno StackPane per sovrapporre testo e immagine
        StackPane buttonContent = new StackPane();
        buttonContent.getStyleClass().add("save-file-button-content");

        // Testo del salvataggio
        Label textLabel = new Label(districtName);
        textLabel.getStyleClass().add("save-file-text");

        // Gestione dell'immagine
        String imagePath = "/com/iss/echoesofdeceit/images/" + imageName + ".jpg";
        File imageFile = null;
        try {
            imageFile = new File(Objects.requireNonNull(getClass().getResource(imagePath)).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        if (imageFile != null && imageFile.exists()) {
            // Carica l'immagine
            Image image = new Image(imageFile.toURI().toString());
            javafx.scene.image.ImageView imageView = new ImageView(image);
            imageView.getStyleClass().add("save-file-image");

            // Applica un filtro per regolare la luminosità
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(-0.5); // Riduce la luminosità
            imageView.setEffect(colorAdjust);

            // Aggiungi l'immagine e il testo al contenuto del pulsante
            buttonContent.getChildren().addAll(imageView, textLabel);
        } else {
            System.out.println("Immagine non trovata");
        }

        // Imposta il contenuto del pulsante
        saveFileButton.setGraphic(buttonContent);
        hboxOfDistricts.getChildren().add(saveFileButton);
        saveFileButton.setOnAction(e -> {

            if(imageName.equals("MainMenu"))
                GameModel.getInstance().setGameScene("WrongNobleDistrict");
            else {
                // Carico la nuova zona
                String district = imageName.replaceAll("\\d.*", "");
                GameModel.getInstance().setCurrentDistrict(district);
                GameModel.getInstance().setCurrentZone(1);
                DistrictModel.getInstance().setDistrict(); //Cambio il distretto da caricare in base al currentDistrict

                // Inizializzo i componenti necessari del PlayerModel
                PlayerModel.getInstance().getCurrentDistrict().replace("DistrictName", GameModel.getInstance().getCurrentDistrict());
                if (!(PlayerModel.getInstance().getCurrentDistrict().get("DistrictName").equals("nobleDistrict")))
                    PlayerModel.getInstance().setRemainingDays(PlayerModel.getInstance().getRemainingDays() - 1);
                PlayerModel.getInstance().getCurrentDistrict().replace("ZoneName", String.valueOf(GameModel.getInstance().getCurrentZone()));
                PlayerModel.getInstance().getLocalItemList().clear();
                PlayerModel.getInstance().getCompletedPuzzles().replaceAll((k, v) -> false); // Imposto tutti i puzzle a false

                ZonePreviewController.isDistrictCompleted = false;
                GameModel.getInstance().setGameScene("ZonePreview");
            }

        });

    }

    public Button getBackToGameButton() {
        return this.backToGameButton;
    }
}
