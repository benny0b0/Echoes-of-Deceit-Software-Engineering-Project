package com.iss.echoesofdeceit.controller;

import com.iss.echoesofdeceit.controller.command.ChangeSceneCommand;
import com.iss.echoesofdeceit.controller.command.Command;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class Pregame {
    @FXML
    private Label infoLabel;
    @FXML
    private Button btnStart;

    private Command changeScene;

    public void initialize(){
        changeScene = new ChangeSceneCommand("Introduction");
        Platform.runLater(()->{
            infoLabel.setText("Fai attenzione durante la tua avventura!\nNella sezione Taccuino del menu di pausa, potrai consultare un resoconto dettagliato di tutto cio' che hai scoperto e fatto finora, cosi' da non perdere mai il filo degli eventi.\nNella sezione Inventario, invece, troverai gli oggetti raccolti durante la partita, utili per risolvere enigmi e proseguire nella storia.\n\n" +
                    "Ricorda: il gioco salva automaticamente ogni volta che completi un puzzle. Buona fortuna!");
            btnStart.setOnAction(e->changeScene.execute());
        });
    }
}
