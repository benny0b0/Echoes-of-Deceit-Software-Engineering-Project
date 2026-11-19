package com.iss.echoesofdeceit.controller.command;

import com.iss.echoesofdeceit.model.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class NextDialogCommand implements Command{
    private DialogModel dialogModel;
    private ImageView oneContainer;
    private ImageView twoContainer;
    private Label name;
    private Label conversationLabel;
    private Timeline typingTimeline; // Timeline globale per il controllo delle animazioni
    private int conversationSize;
    private int index=0;

    public NextDialogCommand(DialogModel dialogModel, ImageView oneContainer, ImageView twoContainer, Label name, Label conversationLabel){
        this.dialogModel = dialogModel;
        this.oneContainer = oneContainer;
        this.twoContainer = twoContainer;
        this.name = name;
        this.conversationLabel = conversationLabel;
        this.conversationSize = conversationSize;
    }

    @Override
    public void execute() {
        if (index < dialogModel.getConversation().size()) {
            update(dialogModel.getConversation().get(index).getId());
            index += 1;
        }
        else {
            //Temporaneo
            String nextSceneValue = DistrictModel.getInstance().getZones().get("zone_"+GameModel.getInstance().getCurrentZone()).getExploration().getImagesMap().get(GameModel.getInstance().getCurrentFocusItemBackground()).getNextScene();
            if (nextSceneValue.equals("Puzzle")){
                if (!PlayerModel.getInstance().getCompletedPuzzles().get(String.valueOf(GameModel.getInstance().getCurrentZone())))
                    GameModel.getInstance().setGameScene(nextSceneValue);
                else
                    GameModel.getInstance().setGameScene("Zone");
            }
            else
                GameModel.getInstance().setGameScene(nextSceneValue);
        }

    }

    private void update(int imageView){
        name.setText(dialogModel.getConversation().get(index).getSpeaker());
        String fullText = dialogModel.getConversation().get(index).getText();

        // Interrompi l'animazione precedente (se esiste)
        if (typingTimeline != null && typingTimeline.getStatus() == Animation.Status.RUNNING) {
            typingTimeline.stop();
        }

        // Avvia una nuova animazione di typing
        displayTypingAnimation(conversationLabel, fullText);

        ImageView primary, secondary;

        switch (imageView) {
            case 1:
                primary = twoContainer;
                secondary = oneContainer;
                break;
            case 2:
                primary = oneContainer;
                secondary = twoContainer;
                break;
            default:
                primary = twoContainer;
                secondary = null;
        }

        applyColorAnimation(primary, true);
        if (secondary != null) {
            applyColorAnimation(secondary, false);
        }
    }

    private void displayTypingAnimation(Label label, String text) {
        label.setText(""); // Inizia con il testo vuoto

        typingTimeline = new Timeline(); // Crea una nuova Timeline

        for (int i = 0; i < text.length(); i++) {
            final int index = i;
            typingTimeline.getKeyFrames().add(
                    new KeyFrame(
                            Duration.millis(20 * i), // 50ms di ritardo per carattere
                            e -> label.setText(text.substring(0, index + 1)) // Mostra i caratteri uno alla volta
                    )
            );
        }

        typingTimeline.setCycleCount(1); // L'animazione viene eseguita una sola volta
        typingTimeline.play(); // Avvia l'animazione
    }

    private void applyColorAnimation(ImageView imageView, boolean isFadeIn) {
        ColorAdjust colorAdjust = new ColorAdjust();
        imageView.setEffect(colorAdjust);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> colorAdjust.setBrightness(isFadeIn ? 0 : -0.5)),
                new KeyFrame(Duration.seconds(0.1), e -> colorAdjust.setBrightness(isFadeIn ? -0.1 : -0.4)),
                new KeyFrame(Duration.seconds(0.2), e -> colorAdjust.setBrightness(isFadeIn ? -0.2 : -0.3)),
                new KeyFrame(Duration.seconds(0.3), e -> colorAdjust.setBrightness(isFadeIn ? -0.3 : -0.2)),
                new KeyFrame(Duration.seconds(0.4), e -> colorAdjust.setBrightness(isFadeIn ? -0.4 : -0.1)),
                new KeyFrame(Duration.seconds(0.5), e -> colorAdjust.setBrightness(isFadeIn ? -0.5 : 0))
        );

        timeline.setCycleCount(1);
        timeline.play();
    }
}
