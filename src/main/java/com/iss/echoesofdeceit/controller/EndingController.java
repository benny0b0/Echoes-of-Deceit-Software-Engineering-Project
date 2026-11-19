package com.iss.echoesofdeceit.controller;

import com.iss.echoesofdeceit.controller.command.ChangeSceneCommand;
import com.iss.echoesofdeceit.controller.command.Command;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class EndingController {
    @FXML
    private Label labelText;
    @FXML
    private Button btnMainMenu;

    private Command changeSceneCommand;

    public void initialize(){
        Platform.runLater(()->{
            changeSceneCommand = new ChangeSceneCommand("MainMenu");
            labelText.setText("Il ladro si concesse un sorriso enigmatico, riconoscendo il valore dell'avversario. L'investigatore aveva dimostrato di essere degno del proprio titolo, cogliendo la vera essenza del complicato enigma.\nCon un gesto teatrale, il ladro lancio' il quadro attraverso la stanza. L'investigatore si getto' istintivamente a terra, allungandosi per afferrarlo. Mentre il dipinto atterrava intatto tra le sue mani, il ladro approfitto' del momento per scattare verso l'uscita.\nSenza esitare, l'investigatore si rialzo' e inizio' l'inseguimento. Il passaggio conduceva verso un giardino interno della reggia. Il ladro tento' di superare un alto cancello. Tuttavia, l'investigatore lo raggiunse proprio mentre stava per scavalcarlo. Con un ultimo scatto, riusci' a tirarlo giu', immobilizzandolo a terra.\nMentre il quadro veniva messo al sicuro, il ladro si lascio' ammanettare senza opporre resistenza. La sfida, pero', non sembrava conclusa: nel suo sguardo enigmatico si intravedeva la promessa di un futuro confronto. L'investigatore lo osservo' mentre veniva condotto via, consapevole che, pur avendo chiuso il caso con successo, il loro gioco di ingegno non era ancora finito.");
            btnMainMenu.setOnAction(e-> changeSceneCommand.execute());
        });
    }
}
