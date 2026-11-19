package com.iss.echoesofdeceit.controller;

import com.iss.echoesofdeceit.controller.command.Command;
import com.iss.echoesofdeceit.controller.command.LoadSaveFileCommand;
import com.iss.echoesofdeceit.model.SaveDataManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Pair;

import java.io.File;
import java.net.URISyntaxException;
import java.util.*;

public class SaveFileController {

    @FXML
    private Button exitSaveChoice;
    @FXML
    private VBox saveFileContainer;

    private Map<String, Map<Character, String>> zones;

    public void initialize(){
        zones = new HashMap<>();
        zones.put("startGame", Map.of('1', "Stanza del quadro", '2', "Ala centrale del museo", '3', "Ingresso secondario"));
        zones.put("historicCenter", Map.of('1', "Stanza del quadro", '2', "Ala centrale del museo", '3', "Ingresso secondario"));
        zones.put("popularDistrict", Map.of('1', "Corso principale", '2', "Casa abbandonata", '3', "Casa abbandonata"));
        zones.put("suburbsDistrict", Map.of('1', "Strada", '2', "Casa abbandonata", '3', "Casa abbandonata"));
        zones.put("academiesDistrict", Map.of('1', "Studio del calligrafo", '2', "Studio del calligrafo", '3', "Via principale"));
        zones.put("marketDistrict", Map.of('1', "Negozio di inchiostri", '2', "Strada secondaria", '3', "Piazza centrale"));
        zones.put("nobleDistrict", Map.of('1', "Ingresso della reggia", '2', "Stanza trappola", '3', "Il calderone"));
    }

    public Button getExitSaveChoice() {
        return this.exitSaveChoice;
    }

    private String formatSaveFileName(String fileName) {
        // Divido la stringa in base a "-"
        String[] parts = fileName.split("-");
        // Rimuovo l'estensione ".json"
        String nameWithoutExtension = parts[1].replace(".json", "");

        char zoneNumber = nameWithoutExtension.charAt(nameWithoutExtension.length() - 1);

        nameWithoutExtension = nameWithoutExtension.substring(0, nameWithoutExtension.length() - 1);

        Map<String, String> districtNames = Map.of(
                "startGame", "Inizio gioco",
                "historicCenter", "Centro Storico",
                "popularDistrict", "Quartiere popolare",
                "suburbsDistrict", "Quartiere Sobborghi",
                "nobleDistrict", "Quartiere Nobiliare",
                "academiesDistrict", "Quartiere Accademie",
                "marketDistrict", "Quartiere Mercato"
        );

        String districtName = districtNames.get(nameWithoutExtension);
        if (districtName == null || !zones.containsKey(nameWithoutExtension)) {
            return fileName; // Return original if district name is not found or zones data is missing
        }

        String zoneDescription = zones.get(nameWithoutExtension).get(zoneNumber);
        return districtName + "\n" + zoneDescription;
    }


    private Button createSaveFileButton(String username, String saveFile) {
        Button saveFileButton = new Button();
        saveFileButton.getStyleClass().add("save-file-button");

        // Crea uno StackPane per sovrapporre testo e immagine
        StackPane buttonContent = new StackPane();
        buttonContent.getStyleClass().add("save-file-button-content");

        // Testo del salvataggio
        String formattedText = formatSaveFileName(saveFile);
        Label textLabel = new Label(formattedText);
        textLabel.getStyleClass().add("save-file-text");
        textLabel.setTextAlignment(TextAlignment.CENTER);

        // Gestione dell'immagine

        // Divido la stringa in base a "-"
        String[] parts = saveFile.split("-");
        String imageBackground = parts[1].replace(".json", "");
        String imagePath = "/com/iss/echoesofdeceit/images/" + imageBackground+"_save";
        String[] extensions = {".jpg", ".png", ".jpeg"};
        File imageFile = null;

        for (String ext : extensions) {
            try {
                imageFile = new File(Objects.requireNonNull(getClass().getResource(imagePath + ext)).toURI());
                if (imageFile.exists()) {
                    break;
                }
            } catch (Exception e) {
                try {
                    imageFile = new File(Objects.requireNonNull(getClass().getResource("/com/iss/echoesofdeceit/images/" + imageBackground + ".jpg").toURI()));
                } catch (URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }


        if (imageFile != null && imageFile.exists()) {
            // Carica l'immagine
            Image image = new Image(imageFile.toURI().toString());
            ImageView imageView = new ImageView(image);
            imageView.getStyleClass().add("save-file-image");

            // Applica un filtro per regolare la luminosità
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(-0.5); // Riduce la luminosità
            imageView.setEffect(colorAdjust);

            // Aggiungi l'immagine e il testo al contenuto del pulsante
            buttonContent.getChildren().addAll(imageView, textLabel);
        } else {
            System.out.println("Immagine non trovata per: " + saveFile);
            buttonContent.getChildren().add(textLabel);
        }

        // Imposta il contenuto del pulsante
        saveFileButton.setGraphic(buttonContent);

        // Evento per caricare il salvataggio
        Command loadSaveFileCommand = new LoadSaveFileCommand(username, saveFile);
        saveFileButton.setOnAction(event -> loadSaveFileCommand.execute());

        return saveFileButton;
    }

    private void loadSaveFiles(String username) {
            List<String> saveFiles = SaveDataManager.getSaveFilesForUser(username);

            // Ordinamento con un Comparatore personalizzato
            Collections.sort(saveFiles, new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    // Estraggo la parte numerica e la utilizzo come criterio di confronto
                    int num1 = Integer.parseInt(s1.split("-")[0]);
                    int num2 = Integer.parseInt(s2.split("-")[0]);
                    return Integer.compare(num1, num2);
                }
            });

            if (saveFiles.isEmpty()) {
                Text noSaveLabel = new Text("Nessun salvataggio trovato per l'utente " + username);
                saveFileContainer.getChildren().add(noSaveLabel);
            }

            for (String saveFile : saveFiles.reversed()) {
                // Usa il metodo createSaveFileButton per creare il bottone
                Button saveFileButton = createSaveFileButton(username, saveFile);
                Command loadSaveFileCommand = new LoadSaveFileCommand(username, saveFile);

                saveFileButton.setOnAction(event -> loadSaveFileCommand.execute());
                saveFileContainer.getChildren().add(saveFileButton);  // Aggiungi il bottone al VBox dentro lo ScrollPane
            }
    }


    public void setUsername(String username) {
        loadSaveFiles(username);
    }
}
