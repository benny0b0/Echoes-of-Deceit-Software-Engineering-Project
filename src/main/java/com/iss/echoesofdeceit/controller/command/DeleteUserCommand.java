package com.iss.echoesofdeceit.controller.command;
import com.iss.echoesofdeceit.model.GameModel;
import com.iss.echoesofdeceit.model.PlayerModel;
import com.iss.echoesofdeceit.model.SaveDataManager;
import javafx.scene.control.ListView;

import java.io.IOException;


public class DeleteUserCommand implements Command{
    private ListView userListView;
    private String name;

    public DeleteUserCommand(ListView userListView, String name){
        this.userListView = userListView;
        this.name = name;
    }
    @Override
    public void execute() {
        String selectedUser = (String) userListView.getSelectionModel().getSelectedItem();
        try {
            SaveDataManager.deleteUser(selectedUser);
            PlayerModel playerModel = PlayerModel.getInstance();
            playerModel.setName(this.name);
            SaveDataManager.createUserFolder(this.name);
            SaveDataManager.initializePlayer(playerModel.getName());
            SaveDataManager.loadPlayer("0-startGame1.json", playerModel.getName());
            GameModel.getInstance().setGameScene("Pregame");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}