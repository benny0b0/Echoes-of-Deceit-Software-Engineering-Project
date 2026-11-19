package com.iss.echoesofdeceit.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class SoundsManager {
    private static SoundsManager instance;
    private double volume = 0.7;
    private MediaPlayer currentPlayer = null;

    public static SoundsManager getInstance() {
        if (instance == null) {
            instance = new SoundsManager();
        }
        return instance;
    }

    public void incrementVolume() {
        if (volume < 1) {
            volume += 0.1;
            volume = Math.round(volume * 10.0) / 10.0; // Arrotonda a 1 decimale
        }
    }

    public void decrementVolume() {
        if (volume > 0) {
            volume -= 0.1;
            volume = Math.round(volume * 10.0) / 10.0; // Arrotonda a 1 decimale
        }
    }

    public double getVolume() {
        return volume;
    }

    // Metodo per riprodurre il suono
    public void playSound(String suono) {
        try {
            if (currentPlayer != null) {
                currentPlayer.stop();
                currentPlayer.dispose();
            }

            Media media = new Media(getClass().getResource("/com/iss/echoesofdeceit/sounds/" + suono + ".mp3").toExternalForm());
            currentPlayer = new MediaPlayer(media);
            currentPlayer.setVolume(volume);

            currentPlayer.setOnEndOfMedia(() -> {
                currentPlayer.stop();
                currentPlayer.dispose();
                currentPlayer = null;
            });

            currentPlayer.play(); // Avvia la riproduzione
        } catch (NullPointerException e) {
            System.out.println("Errore: file audio non trovato. Percorso: " + "/com/iss/echoesofdeceit/sounds/" + suono + ".mp3");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Metodo per riprodurre il suono con durata limitata (opzionale)
    public void playSound(String suono, double durata) {
        try {
            Media media = new Media(getClass().getResource("/com/iss/echoesofdeceit/sounds/" + suono + ".mp3").toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(volume);
            mediaPlayer.setStopTime(new Duration(durata * 1000)); // Imposta la durata del suono
            mediaPlayer.play();
            mediaPlayer.setOnEndOfMedia(() -> {
                mediaPlayer.stop();
                mediaPlayer.dispose();
            });
        } catch (NullPointerException e) {
            System.out.println("Errore: file audio non trovato. Percorso: " + "/com/iss/echoesofdeceit/sounds/" + suono + ".mp3");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
