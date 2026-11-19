package com.iss.echoesofdeceit.model;

import javafx.scene.media.MediaPlayer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SoundsManagerTest {

   String currentPlayer = "Test";
    @Test
    void testIncrementVolume() {
        SoundsManager manager = SoundsManager.getInstance();
        manager.incrementVolume();
        assertEquals(0.8, manager.getVolume());
    }




    @Test
    void testPlaySound() {
        SoundsManager manager = SoundsManager.getInstance();
        manager.playSound("testSound");
        assertNotNull(currentPlayer);
    }

    @Test
    void testPlaySoundWithDuration() {
        SoundsManager manager = SoundsManager.getInstance();
        manager.playSound("testSound", 2.0);
        assertNotNull(currentPlayer);
    }




}
