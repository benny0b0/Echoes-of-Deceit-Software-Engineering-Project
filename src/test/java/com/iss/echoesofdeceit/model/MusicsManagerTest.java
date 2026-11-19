package com.iss.echoesofdeceit.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MusicsManagerTest {

    private MusicsManager musicsManager;

    @BeforeEach
    void setUp() {
        musicsManager = MusicsManager.getInstance();
    }

    @Test
    void testMusicsManagerSingleton() {
        MusicsManager anotherInstance = MusicsManager.getInstance();
        assertSame(musicsManager, anotherInstance, "MusicsManager non è singleton!");
    }

    @Test
    void testStopMusicWithoutError() {
        assertDoesNotThrow(() -> musicsManager.stopMusic(), "StopMusic ha lanciato un'eccezione!");
    }

    @Test
    void testPlayMusicWithNullTrack() {
        assertDoesNotThrow(() -> musicsManager.playMusic(null), "PlayMusic non dovrebbe lanciare eccezioni con traccia nulla!");
    }
}
