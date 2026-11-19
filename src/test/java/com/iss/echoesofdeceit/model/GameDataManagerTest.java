package com.iss.echoesofdeceit.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

class GameDataManagerTest {

    @TempDir
    Path tempDir;




    @Test
    void testSaveQuartiere() throws IOException {
        String quartiereName = "testQuartiere";
        String jsonContent = """
            {
                "testQuartiere": {
                    "zones": {}
                }
            }
        """;
        Path tempFile = tempDir.resolve("test.json");
        Files.writeString(tempFile, jsonContent);

        DistrictModel districtModel = new DistrictModel();
        Map<String, ZoneModel> zones = Map.of(
                "zone1", new ZoneModel(),
                "zone2", new ZoneModel()
        );
        districtModel.setZones(zones);

        GameDataManager.saveQuartiere(tempFile.toString(), quartiereName, districtModel);

        String updatedJson = Files.readString(tempFile);
        assertTrue(updatedJson.contains("\"zone1\""));
        assertTrue(updatedJson.contains("\"zone2\""));
    }

    @Test
    void testSaveQuartiereNonEsistente() throws IOException {
        String quartiereName = "quartiereInesistente";
        String jsonContent = """
            {
                "altroQuartiere": {
                    "zones": {}
                }
            }
        """;
        Path tempFile = tempDir.resolve("test.json");
        Files.writeString(tempFile, jsonContent);

        DistrictModel districtModel = new DistrictModel();
        Map<String, ZoneModel> zones = Map.of(
                "zone1", new ZoneModel()
        );
        districtModel.setZones(zones);

        assertThrows(IllegalArgumentException.class, () -> GameDataManager.saveQuartiere(tempFile.toString(), quartiereName, districtModel));
    }
}
