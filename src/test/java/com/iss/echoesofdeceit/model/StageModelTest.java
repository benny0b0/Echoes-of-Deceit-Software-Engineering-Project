package com.iss.echoesofdeceit.model;

import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StageModelTest {

    private Stage stageMock;

    @BeforeEach
    void setUp() {
        stageMock = mock(Stage.class);
    }

    @Test
    void testStageModelSingleton() {
        StageModel stageModel1 = StageModel.getInstance(stageMock);
        StageModel stageModel2 = StageModel.getInstance(stageMock);
        assertSame(stageModel1, stageModel2, "StageModel non è singleton!");
    }
}
