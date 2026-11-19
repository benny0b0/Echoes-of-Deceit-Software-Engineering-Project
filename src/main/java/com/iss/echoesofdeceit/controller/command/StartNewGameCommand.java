package com.iss.echoesofdeceit.controller.command;

import com.iss.echoesofdeceit.controller.AlertDeleteUserController;
import com.iss.echoesofdeceit.model.DistrictModel;
import com.iss.echoesofdeceit.model.GameModel;
import com.iss.echoesofdeceit.model.PlayerModel;
import com.iss.echoesofdeceit.model.SaveDataManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.PointLight;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.List;

public class StartNewGameCommand implements Command{

    private StackPane stackPane;
    private TextField name;
    private Label errorLabel;

    public StartNewGameCommand(StackPane stackPane, TextField name, Label errorLabel){
        this.stackPane = stackPane;
        this.name = name;
        this.errorLabel = errorLabel;
    }

    @Override
    public void execute() {
        if (name.getText().isEmpty()) {
            errorLabel.setText("Errore: Il campo nome non puo' essere lasciato vuoto!");
        } else if (!name.getText().matches("^[a-zA-Z_][a-zA-Z0-9_]{0,14}$")) {
            errorLabel.setText("Errore: Il nome utente non e' valido!\n‧ Deve iniziare con una lettera\n‧ Puo' contenere lettere, numeri e underscore (_)");
        } else {
            List<String> existingUsers = SaveDataManager.getExistingUsers();

            if (existingUsers.contains(name.getText())) {
                errorLabel.setText("Errore: L'utente " + name.getText() + " esiste gia'");
            } else if (existingUsers.size() >= 3) {
                askToDeleteUser();
            }
            else {
                try {
                    PlayerModel playerModel = PlayerModel.getInstance();
                    playerModel.setName(this.name.getText());
                    SaveDataManager.createUserFolder(this.name.getText());
                    SaveDataManager.initializePlayer(playerModel.getName());
                    SaveDataManager.loadPlayer("0-startGame1.json", playerModel.getName());
                    GameModel.getInstance().setGameScene("Pregame");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void askToDeleteUser() {
        try {
            // Mostra il dialogo per selezionare un utente da eliminare
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/iss/echoesofdeceit/view/AlertDeleteUser.fxml"));
            StackPane pane = loader.load();
            AlertDeleteUserController controller = loader.getController();
            controller.setName(this.name.getText());
            stackPane.getChildren().add(pane);
            controller.getCloseButton().setOnAction(actionEvent -> stackPane.getChildren().remove(pane));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Errore durante la gestione degli utenti.");
        }
    }
}
