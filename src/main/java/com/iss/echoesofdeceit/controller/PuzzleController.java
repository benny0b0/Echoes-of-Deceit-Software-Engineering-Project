package com.iss.echoesofdeceit.controller;

import com.iss.echoesofdeceit.controller.command.*;
import com.iss.echoesofdeceit.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.*;

public class PuzzleController {
    public static List<Button> listDecrementsButtons;
    public static List<Button> listIncrementsButtons;
    @FXML
    private StackPane centerStackPane;
    @FXML
    private StackPane mainStackPane;
    @FXML
    private Label title;
    @FXML
    private Label description;
    @FXML
    private Button resetButton;
    @FXML
    private Button confirmButton;
    @FXML
    private Button hint;
    @FXML
    private Button pause;

    // Comandi
    private Command resetPuzzleCommand;
    private Command hintCommand;
    private Command pauseCommand;

    // Attributi
    public static List<Object> list; //Lista di actionIds
    public static Map<Button, Label> mappa;
    private DistrictModel districtModel;
    private PuzzleContext context;


    public PuzzleController() throws IOException {
        PuzzleController.list = new ArrayList<>();
        PuzzleController.listDecrementsButtons = new ArrayList<>();
        PuzzleController.listIncrementsButtons = new ArrayList<>();
        PuzzleController.mappa = new HashMap<>();
        this.districtModel = DistrictModel.getInstance();
        this.context = new PuzzleContext();
    }

    @FXML
    public void initialize() {
        int currentPuzzleIndex = GameModel.getInstance().getCurrentPuzzle();
        int currentZoneIndex = GameModel.getInstance().getCurrentZone();
        Map<String, ZoneModel> zones = districtModel.getZones();
        ZoneModel zone = zones.get("zone_" + currentZoneIndex);
        PuzzleModel puzzle = zone.getPuzzle().get(currentPuzzleIndex);
        PuzzleComposite puzzleStackpane = puzzle.getStackpane();

        switch (puzzle.getId()) {
            case 1:
                context.setStrategy(new ConcreteStrategy1());
                break;
            case 2,3:
                context.setStrategy(new ConcreteStrategy2());
                break;
            default:
                System.err.println("id puzzle non trovato");
        }
        Platform.runLater(() -> {
            // Creazione dei comandi
            this.resetPuzzleCommand = new ResetPuzzleCommand();
            this.hintCommand = new HintCommand(this.mainStackPane, puzzle.getHint());
            this.pauseCommand = new PauseCommand(this.mainStackPane);

            // Collega i pulsanti ai comandi
            hint.setOnAction(e -> hintCommand.execute());
            pause.setOnAction(e -> pauseCommand.execute());
            resetButton.setOnAction(e -> resetPuzzleCommand.execute());

            // Esegui strategia
            confirmButton.setOnAction(event -> {
                try {

                    if( puzzle.getId() == 3)
                        list.add(PuzzleElement.getTextAreaString());
                    context.executeStrategy(list);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        });

        if (zone == null || zone.getPuzzle().isEmpty()) {
            System.out.println("Zona o puzzle non trovato");
            return;
        }
        String bgPath = String.valueOf(Objects.requireNonNull(getClass().getResource("/com/iss/echoesofdeceit/images/" + GameModel.getInstance().getCurrentDistrict()+GameModel.getInstance().getCurrentZone()+".jpg")));
        mainStackPane.setStyle("-fx-background-image: url('" + bgPath + "');");
        title.setText(puzzle.getTitle());
        description.setText(puzzle.getDescription());

        Node puzzleNode = puzzleStackpane.render(); // Usa il metodo render() per creare i nodi
        centerStackPane.getChildren().clear();
        centerStackPane.getChildren().add(puzzleNode);

        this.setupCharacterChangeActions();
    }


    private void setupCharacterChangeActions() {
        for (int i = 0; i < PuzzleController.listDecrementsButtons.size(); i++) {
            int finalI = i;
            // Evento per il bottone di decremento
            PuzzleController.listDecrementsButtons.get(i).setOnAction(e -> {
                PuzzleController.listIncrementsButtons.get(finalI).setDisable(false);
                // Decrementare la label
                String label = ((Label) PuzzleController.list.get(finalI)).getText();
                StringBuilder result = new StringBuilder(label);

                for (int j = 0; j < result.length(); j++) {
                    char currentChar = result.charAt(j);
                    char newChar;

                    if (currentChar == 'a') {
                        newChar = 'z'; // Se è 'a', diventa 'z'
                    } else if (currentChar == 'A') {
                        newChar = 'Z'; // Se è 'A', diventa 'Z'
                    } else if (Character.isLetter(currentChar)) {
                        newChar = (char)(currentChar - 1); // Altrimenti decrementa il carattere
                    } else {
                        newChar = currentChar; // Mantieni il carattere se non è una lettera
                    }

                    result.setCharAt(j, newChar); // Setta il nuovo carattere
                }

                if (result.toString().equals("a")) {
                    SoundsManager.getInstance().playSound("unlock");
                } else if (result.toString().matches("[b-z]")) {
                    SoundsManager.getInstance().playSound("lock");
                }
                ((Label) PuzzleController.list.get(finalI)).setText(result.toString());
            });

            // Evento per il bottone di incremento
            PuzzleController.listIncrementsButtons.get(i).setOnAction(e -> {
                PuzzleController.listDecrementsButtons.get(finalI).setDisable(false);
                // Incrementare la label
                String label = ((Label) PuzzleController.list.get(finalI)).getText();
                StringBuilder result = new StringBuilder(label);
                for (int j = 0; j < result.length(); j++) {
                    char currentChar = result.charAt(j);
                    char newChar;
                    if (currentChar == 'z') {
                        newChar = 'a';
                    } else if (currentChar == 'Z') {
                        newChar = 'A';
                    } else if (Character.isLetter(currentChar)) {
                        newChar = (char)(currentChar + 1);
                    } else {
                        newChar = currentChar;
                    }
                    result.setCharAt(j, newChar);
                }
                if (result.toString().equals("a")) {
                    SoundsManager.getInstance().playSound("unlock");
                } else if (result.toString().matches("[b-z]")) {
                    SoundsManager.getInstance().playSound("lock");
                }
                ((Label) PuzzleController.list.get(finalI)).setText(result.toString());
            });
        }
    }

}



