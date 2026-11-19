package com.iss.echoesofdeceit.controller;

import com.iss.echoesofdeceit.controller.command.ChangeSceneCommand;
import com.iss.echoesofdeceit.controller.command.Command;
import com.iss.echoesofdeceit.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.Objects;


public class PauseMenuController {
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
    @FXML
    private Button btnMainMenu;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label remainingDaysLabel;
    @FXML
    private Button btnExitPause;
    @FXML
    private Text diaryLabel;
    @FXML
    private VBox vboxItems;
    @FXML
    private StackPane stackPane; // StackPane principale
    @FXML
    private StackPane inventoryStackPane;


    private Command changeSceneCommand;

    @FXML
    public void initialize(){
        Platform.runLater(() -> {
            changeSceneCommand = new ChangeSceneCommand("MainMenu");

            // Popolo l'inventario
            for(Item item : PlayerModel.getInstance().getInventory().getItems()){
                // Hbox
                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER_LEFT);
                hBox.setPrefWidth(Region.USE_COMPUTED_SIZE);
                hBox.setSpacing(10);

                // Percorso dell'immagine
                String immagine = item.getImage();
                Image image = new Image(String.valueOf(Objects.requireNonNull(getClass().getResource("/com/iss/echoesofdeceit/images/" + immagine))));
                ImageView imageView = new ImageView(image);
                // Impostazioni dell'immagine
                imageView.setOnMouseEntered(event -> imageView.setCursor(Cursor.HAND));
                imageView.setOnMouseExited(event -> imageView.setCursor(Cursor.DEFAULT));
                imageView.setOnMouseClicked(e -> {
                    this.itemDetails(item);
                });
                imageView.setFitWidth(60);
                imageView.setPreserveRatio(true);
                hBox.getChildren().add(imageView);

                Label nome = new Label(item.getName()); // nome dell'item
                // Impostazioni nome item
                nome.setWrapText(true);
                nome.setAlignment(Pos.CENTER);
                nome.getStyleClass().add("item-name");

                Region leftSpacer = new Region();
                Region rightSpacer = new Region();

                HBox.setHgrow(leftSpacer, Priority.ALWAYS);
                HBox.setHgrow(rightSpacer, Priority.ALWAYS);

                hBox.getChildren().addAll(leftSpacer, nome, rightSpacer);
                vboxItems.getChildren().add(hBox);
            }

            diaryLabel.setText(PlayerModel.getInstance().getNotebook().getDiary());
            btnMainMenu.setOnAction(event -> {
                ZonePreviewController.isDistrictCompleted=false;
                changeSceneCommand.execute();
            });
            userNameLabel.setText("Utente: "+PlayerModel.getInstance().getName());
            remainingDaysLabel.setText("Giorni rimanenti: "+ PlayerModel.getInstance().getRemainingDays());

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

        });
    }

    public Button getBtnExitPause() {
        return btnExitPause;
    }

    // Metodo per visualizzare i dettagli di un item
    public void itemDetails(Item item) {
        StackPane childStackPane = new StackPane();
        childStackPane.getStyleClass().add("stack-pane-item-details");
//        childStackPane.setMaxWidth(800);
//        childStackPane.setMaxHeight(600);

        // Percorso dell'immagine
        String immagine = item.getImage();
        Image image = new Image(String.valueOf(Objects.requireNonNull(getClass().getResource("/com/iss/echoesofdeceit/images/" + immagine))));
        ImageView imageView1 = new ImageView(image);
        // Impostazioni dell'immagine
        imageView1.setPreserveRatio(true);
        imageView1.setFitWidth(500);

        // Pannelli ed elementi
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.getStyleClass().add("vbox-item-details");

        Label label = new Label(item.getDescription());
        label.getStyleClass().add("item-description");
        label.setWrapText(true);

        Button backToMenuButton = new Button("Indietro");
        backToMenuButton.getStyleClass().add("button-item-details");
        backToMenuButton.setOnAction(e -> this.stackPane.getChildren().remove(childStackPane));


        vb.getChildren().add(imageView1);
        vb.getChildren().add(label);
        vb.getChildren().add(backToMenuButton);

        label.setStyle("-fx-text-alignment: center; -fx-alignment: center;");
        backToMenuButton.setAlignment(Pos.CENTER);

        // Aggiungo i componenti allo stackPane
        VBox.setVgrow(imageView1, Priority.ALWAYS);
        childStackPane.getChildren().add(vb);
        this.stackPane.getChildren().add(childStackPane);
    }
}
