package com.iss.echoesofdeceit.controller.command;

import com.iss.echoesofdeceit.controller.ZoneController;
import com.iss.echoesofdeceit.controller.command.BackItemCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class BackItemCommandTest {

    private ZoneController zoneController;
    private BackItemCommand backItemCommand;

    @BeforeEach
    void setUp() {
        zoneController = mock(ZoneController.class);
        backItemCommand = new BackItemCommand(zoneController);
    }

    @Test
    void execute_ShouldDecrementCurrentPosition() {
        List<String> mockImageList = new ArrayList<>();
        mockImageList.add("image1");
        mockImageList.add("image2");
        mockImageList.add("image3");

        when(zoneController.getCurrentPosition()).thenReturn(2);
        when(zoneController.getImageList()).thenReturn(mockImageList);

        backItemCommand.execute();

        verify(zoneController).setCurrentPosition(1);
        verify(zoneController).loadCurrentBackground();
    }



    @Test
    void execute_ShouldCallLoadCurrentBackground() {
        List<String> mockImageList = new ArrayList<>();
        mockImageList.add("image1");
        mockImageList.add("image2");
        mockImageList.add("image3");

        when(zoneController.getCurrentPosition()).thenReturn(1);
        when(zoneController.getImageList()).thenReturn(mockImageList);

        backItemCommand.execute();

        verify(zoneController).loadCurrentBackground();
    }
}
