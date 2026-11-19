package com.iss.echoesofdeceit.controller;

import com.iss.echoesofdeceit.model.GameModel;
import com.iss.echoesofdeceit.model.StageModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class StageControllerTest {
    private StageController controller;
    private GameModel mockGameModel;
    private StageModel mockStageModel;

    @BeforeEach
    void setUp() {
        mockGameModel = mock(GameModel.class);
        mockStageModel = mock(StageModel.class);
        controller = new StageController(mockGameModel, mockStageModel);
    }

    @Test
    void testUpdate() {
        String testState = "TestState";
        controller.update(testState);
        verify(mockStageModel, times(1)).setCurrentScene(testState);
    }

    @Test
    void testConstructorAddsObserver() {
        verify(mockGameModel, times(1)).addObserver(controller);
    }
}
