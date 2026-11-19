package com.iss.echoesofdeceit.model;

import com.iss.echoesofdeceit.controller.Observer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameModelTest {

    private GameModel gameModel;

    @BeforeEach
    void setUp() {
        gameModel = GameModel.getInstance();
    }

    @Test
    void testGameModelSingleton() {
        GameModel anotherInstance = GameModel.getInstance();
        assertSame(gameModel, anotherInstance, "GameModel non è singleton!");
    }

    @Test
    void testSetAndGetGameScene() {
        String sceneName = "testScene";
        gameModel.setGameScene(sceneName);
        assertEquals(sceneName, gameModel.getGameScene(), "Il nome della scena non corrisponde!");
    }

    @Test
    void testAddAndRemoveObservers() {
        Observer observerMock = mock(Observer.class);
        gameModel.addObserver(observerMock);

        gameModel.setGameScene("newScene");
        verify(observerMock, times(1)).update("newScene");

        gameModel.removeObserver(observerMock);
        gameModel.setGameScene("anotherScene");
        verifyNoMoreInteractions(observerMock);
    }
}
