package com.iss.echoesofdeceit.controller.command;

import com.iss.echoesofdeceit.model.GameModel;
import com.iss.echoesofdeceit.model.PlayerModel;
import com.iss.echoesofdeceit.model.SaveDataManager;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NextCommand implements Command {
    private static final double TRANSITION_DURATION_SECONDS = 0.3; // Variabile configurabile per la durata delle transizioni

    private int count = 1;
    private StackPane introductionContainer;
    private Label glassLabel;
    private Button btnSkip;

    public NextCommand(StackPane introductionContainer, Label glassLabel, Button btnSkip) {
        this.introductionContainer = introductionContainer;
        this.glassLabel = glassLabel;
        this.btnSkip = btnSkip;
    }

    @Override
    public void execute() {
        count++;
        handleSceneTransition(count);
    }

    private void handleSceneTransition(int step) {
        switch (step) {
            case 2 -> transitionScene(
                    "IntroductionContainer2",
                    "Quella serenita' venne infranta all'improvviso, come un vetro che si spezza nel silenzio. Odill era in fermento per l'esposizione di un'opera straordinaria, non un semplice capolavoro, ma il simbolo stesso della citta'. Un dipinto unico, capace di catturare l'anima di quel luogo e attirare sguardi da ogni angolo del mondo. Ma qualcosa ando' storto, qualcosa di oscuro e inaspettato. I sussurri si propagarono come un'ombra per le strade, e una tensione sottile prese a serpeggiare tra la folla, segnando l'inizio di un mistero inquietante."
            );
            case 3 -> {
                transitionScene(
                        "IntroductionContainer3",
                        "Il momento tanto atteso giunse. La sala del museo era gremita, ogni sguardo fisso sul quadro coperto da un drappo rosso. " +
                                "Ma quando il velo venne rimosso, il mormorio di stupore lascio' presto spazio a uno di incredulita': " +
                                "il quadro esposto era un falso. Accanto alla tela, pero', c'era qualcosa di ancora piu' sorprendente: " +
                                "una lettera misteriosa. L'investigatore presente tra il pubblico capi' subito che il messaggio non era rivolto a chiunque, questo era certo. " +
                                "Un furto perfetto, mascherato da un enigma che avrebbe messo alla prova le menti piu' brillanti."
                );
                btnSkip.setDisable(true);
            }
            case 4 -> GameModel.getInstance().setGameScene("ZonePreview");
        }
    }

    private void transitionScene(String cssClass, String newText) {
        changeBackgroundWithTransition(introductionContainer, cssClass);
        changeTextWithTransition(glassLabel, newText);
    }

    private void changeBackgroundWithTransition(StackPane container, String cssClass) {
        applyFadeTransition(container, () -> {
            container.getStyleClass().clear();
            container.getStyleClass().add(cssClass);
        });
    }

    private void changeTextWithTransition(Label label, String newText) {
        applyColorTransition(label, () -> label.setText(newText));
    }

    private void applyColorTransition(Label label, Runnable onFinishedAction) {
        // Transizione da bianco a trasparente
        Timeline fadeOut = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(label.textFillProperty(), Color.WHITE)),
                new KeyFrame(Duration.seconds(TRANSITION_DURATION_SECONDS), new KeyValue(label.textFillProperty(), Color.TRANSPARENT))
        );

        fadeOut.setOnFinished(event -> {
            onFinishedAction.run();

            // Transizione da trasparente a bianco
            Timeline fadeIn = new Timeline(
                    new KeyFrame(Duration.seconds(0), new KeyValue(label.textFillProperty(), Color.TRANSPARENT)),
                    new KeyFrame(Duration.seconds(TRANSITION_DURATION_SECONDS), new KeyValue(label.textFillProperty(), Color.WHITE))
            );

            fadeIn.play();
        });

        fadeOut.play();
    }

    private void applyFadeTransition(javafx.scene.Node node, Runnable onFinishedAction) {
        Timeline fadeOut = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(node.opacityProperty(), 1.0)),
                new KeyFrame(Duration.seconds(TRANSITION_DURATION_SECONDS), new KeyValue(node.opacityProperty(), 0.0))
        );

        fadeOut.setOnFinished(event -> {
            onFinishedAction.run();

            Timeline fadeIn = new Timeline(
                    new KeyFrame(Duration.seconds(0), new KeyValue(node.opacityProperty(), 0.0)),
                    new KeyFrame(Duration.seconds(TRANSITION_DURATION_SECONDS), new KeyValue(node.opacityProperty(), 1.0))
            );

            fadeIn.play();
        });

        fadeOut.play();
    }
}
