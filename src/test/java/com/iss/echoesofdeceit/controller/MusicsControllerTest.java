package com.iss.echoesofdeceit.controller;

import com.iss.echoesofdeceit.model.MusicsManager;
import com.iss.echoesofdeceit.model.GameModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class MusicsControllerTest {

    private GameModel gameModel;
    private MusicsManager musicsManager;
    private MusicsController musicsController;

    @BeforeEach
    void setUp() {
        gameModel = mock(GameModel.class);
        musicsManager = mock(MusicsManager.class);

        musicsController = new MusicsController(gameModel, musicsManager);
    }

    @Test
    void testMusicsControllerReceivesUpdate() {
        String newState = "newTrack";
        musicsController.update(newState);


        verify(musicsManager, times(1)).playMusic(newState);
    }

    @Test
    void testObserverRegistration() {
        verify(gameModel, times(1)).addObserver(musicsController);
    }
}
