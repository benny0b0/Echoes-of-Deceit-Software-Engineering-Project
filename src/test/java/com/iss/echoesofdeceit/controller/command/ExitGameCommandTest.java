package com.iss.echoesofdeceit.controller.command;

import com.iss.echoesofdeceit.model.StageModel;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;

class ExitGameCommandTest {

    private ExitGameCommand exitGameCommand;
    private StageModel stageModel;
    private Stage stage;

    @BeforeEach
    void setUp() {
        exitGameCommand = new ExitGameCommand();
        stageModel = mock(StageModel.class);
        stage = mock(Stage.class);
    }

    @Test
    void testExecute() {
        try (MockedStatic<StageModel> mockedStatic = mockStatic(StageModel.class)) {
            mockedStatic.when(() -> StageModel.getInstance(null)).thenReturn(stageModel);


            when(stageModel.getStage()).thenReturn(stage);


            exitGameCommand.execute();

            verify(stage).close();
        }
    }
}