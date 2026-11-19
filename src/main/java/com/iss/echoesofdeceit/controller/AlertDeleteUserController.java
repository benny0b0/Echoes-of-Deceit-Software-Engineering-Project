package com.iss.echoesofdeceit.controller;

import com.iss.echoesofdeceit.controller.command.Command;
import com.iss.echoesofdeceit.controller.command.DeleteUserCommand;
import com.iss.echoesofdeceit.model.SaveDataManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import java.util.List;

public class AlertDeleteUserController {

    @FXML
    private Button closeButton;
    @FXML
    private Button confirmDeleteButton;
    @FXML
    private ListView<String> userListView;
    @FXML
    private Label infoLabel;

    private Command deleteUserCommand;
    private String name;

    public Button getCloseButton() {
        return closeButton;
    }

    @FXML
    private void initialize() {
        Platform.runLater(()->{
            infoLabel.setText("Dopo aver completato questa scelta, potrai iniziare la tua partita come "+ this.name +". Sei sicuro di voler procedere?");
            deleteUserCommand = new DeleteUserCommand(userListView, name);
            showUserList();
            userListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                // Se un elemento è selezionato, abilita il pulsante
                confirmDeleteButton.setDisable(newValue == null);
            });
            confirmDeleteButton.setOnAction(event -> deleteUserCommand.execute());
        });
    }

    // Mostra la lista degli utenti da eliminare
    private void showUserList() {
        List<String> users =  SaveDataManager.getExistingUsers();
        // Filtra gli utenti per rimuovere quelli vuoti o nulli
        users.removeIf(user -> user == null || user.trim().isEmpty());

        // Aggiungi gli utenti filtrati alla ListView
        userListView.getItems().clear();
        userListView.getItems().addAll(users);
    }

    public void setName(String name) {
        this.name = name;
    }
}
