package com.iss.echoesofdeceit.controller;

import com.iss.echoesofdeceit.controller.command.Command;
import com.iss.echoesofdeceit.controller.command.ContinueGameCommand;
import com.iss.echoesofdeceit.controller.command.ExitGameCommand;
import com.iss.echoesofdeceit.controller.command.NewGameCommand;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import java.io.File;

public class MainMenuController {
    @FXML
    private StackPane mainMenuContainer;
    @FXML
    private Button btnNewGame;
    @FXML
    private Button btnContinue;
    @FXML
    private Button btnExit;

    private Command newGameCommand;
    private Command continueCommand;
    private Command exitCommand;

    // Metodo di inizializzazione della scena
    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            newGameCommand = new NewGameCommand(this.mainMenuContainer);
            continueCommand = new ContinueGameCommand(this.mainMenuContainer);
            exitCommand = new ExitGameCommand();

            btnNewGame.setOnAction(event -> newGameCommand.execute());
            btnContinue.setOnAction(event -> continueCommand.execute());
            btnExit.setOnAction(event -> exitCommand.execute());

            checkContinueButton();
        });
    }

    private void checkContinueButton() {
        // Percorso relativo alla cartella "saves"
        String savesPath = "src/main/resources/com/iss/echoesofdeceit/saves"; // Percorso della cartella "saves"
        File savesFolder = new File(savesPath);

        if (!savesFolder.exists() || !savesFolder.isDirectory()) {
            System.err.println("La cartella 'saves' non esiste o non è una cartella.");
        }

        // Ottieni la lista delle sottocartelle all'interno della cartella "saves"
        File[] userFolders = savesFolder.listFiles(File::isDirectory);

        if (!(userFolders == null || userFolders.length == 0)) {
            // Se ci sono sottocartelle, abilita il pulsante "Continua"
            btnContinue.setDisable(false);  // Abilita il pulsante
        }
    }

}
