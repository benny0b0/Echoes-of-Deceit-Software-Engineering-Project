package com.iss.echoesofdeceit.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;

public class MusicsManager {
    private MediaPlayer mediaPlayer;
    private String currentTrack; // Nome della traccia attualmente in riproduzione
    private static MusicsManager instance;
    private  double volume=0.2;

    // Metodo per ottenere l'istanza singleton
    public static MusicsManager getInstance() {
        if (instance == null) {
            instance = new MusicsManager();
        }
        return instance;
    }

    public void incrementVolume() {
        if (volume < 1) {
            volume += 0.1;
            volume = Math.round(volume * 10.0) / 10.0; // Arrotonda a 1 decimale
            mediaPlayer.setVolume(volume);
        }
    }


    public double getVolume() {
        return volume;
    }

    public void decrementVolume(){
        if (volume > 0) {
            volume -= 0.1;
            volume = Math.round(volume * 10.0) / 10.0; // Arrotonda a 1 decimale
            mediaPlayer.setVolume(volume);
        }
    }


    // Metodo per riprodurre la musica e gestire fade-in e fade-out
    public void playMusic(String trackName) {
        try {
            // Controlla se la traccia è già in riproduzione
            if (trackName.equals(currentTrack) && mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                return; // Se sì, non fa nulla
            }

            URL url = getClass().getResource("/com/iss/echoesofdeceit/musics/" + trackName + ".mp3");

            if (url != null) {
                // Ferma la musica precedente con fade-out
                fadeOutCurrentTrack(() -> {
                    String uri = url.toExternalForm();

                    Platform.runLater(() -> {
                        try {
                            Media media = new Media(uri);
                            mediaPlayer = new MediaPlayer(media);
                            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                            mediaPlayer.setVolume(0); // Inizialmente il volume è 0 per il fade-in
                            mediaPlayer.play();
                            fadeInCurrentTrack();
                            currentTrack = trackName; // Aggiorna il nome della traccia corrente
                        } catch (Exception e) {
                            System.err.println("Errore nella creazione del MediaPlayer: " + e.getMessage());
                            e.printStackTrace();
                        }
                    });
                });
            }
        } catch (Exception e) {
            System.err.println("Eccezione: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Metodo per ridurre gradualmente il volume e fermare la traccia (fade-out)
    private void fadeOutCurrentTrack(Runnable onFadeComplete) {
        if (mediaPlayer != null) {
            Timeline fadeOut = createFadeTimeline(volume, 0.0, 1.0);
            fadeOut.setOnFinished(event -> {
                stopMusic(); // Ferma la traccia quando il fade-out è completo
                if (onFadeComplete != null) {
                    onFadeComplete.run();
                }
            });
            fadeOut.play();
        } else {
            if (onFadeComplete != null) {
                onFadeComplete.run();
            }
        }
    }

    // Metodo per aumentare gradualmente il volume della traccia (fade-in)
    private void fadeInCurrentTrack() {
        if (mediaPlayer != null) {
            Timeline fadeIn = createFadeTimeline(0.0, volume, 1.0);
            fadeIn.play();
        }
    }

    // Metodo generico per creare una timeline di fade-in o fade-out
    private Timeline createFadeTimeline(double fromVolume, double toVolume, double durationInSeconds) {
        Timeline fadeTimeline = new Timeline();
        for (int i = 0; i <= 10; i++) {
            final double volumeStep = fromVolume + (toVolume - fromVolume) * i / 10;
            fadeTimeline.getKeyFrames().add(new KeyFrame(
                    Duration.millis(i * durationInSeconds * 100),
                    event -> mediaPlayer.setVolume(volumeStep)
            ));
        }
        return fadeTimeline;
    }

    // Metodo per fermare la traccia
    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            currentTrack = null; // Resetta il nome della traccia corrente
        }
    }
}
