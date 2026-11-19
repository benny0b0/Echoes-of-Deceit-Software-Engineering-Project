package com.iss.echoesofdeceit.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.IOException;
import java.util.Map;

class DistrictModelTest {

    @Mock
    private GameDataManager gameDataManagerMock;

    @Mock
    private Map<String, ZoneModel> zonesMock;

    private DistrictModel districtModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        districtModel = new DistrictModel();

        try (MockedStatic<GameModel> mockedGameModel = mockStatic(GameModel.class)) {
            mockedGameModel.when(GameModel::getInstance).thenReturn(mock(GameModel.class));

            GameModel gameModelMock = GameModel.getInstance();
            when(gameModelMock.getCurrentDistrict()).thenReturn("testDistrict");
        }
    }


    @Test
    void testGetZones_shouldReturnCorrectZones() {
        districtModel.setZones(zonesMock);

        Map<String, ZoneModel> result = districtModel.getZones();

        assertNotNull(result, "Zone non nulla");
        assertEquals(zonesMock, result, "Zona corretta");
    }
}

