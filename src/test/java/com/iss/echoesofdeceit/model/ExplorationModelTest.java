package com.iss.echoesofdeceit.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

class ExplorationModelTest {

    @Test
    void testGetAndSetRoomName() {
        ExplorationModel explorationModel = new ExplorationModel();
        String nomeStanza = "Sala Investigativa";

        explorationModel.setRoomName(nomeStanza);

        String risultato = explorationModel.getRoomName();

        assertEquals(nomeStanza, risultato, "Il nome della stanza dovrebbe essere correttamente impostato e recuperato.");
    }

    @Test
    void testGetAndSetImageList() {
        ExplorationModel explorationModel = new ExplorationModel();
        List<String> immagini = new ArrayList<>();
        immagini.add("image1.jpg");
        immagini.add("image2.jpg");
        explorationModel.setImageList(immagini);

        List<String> risultato = explorationModel.getImageList();

        assertEquals(immagini, risultato, "La lista delle immagini dovrebbe essere correttamente impostata e recuperata.");
    }

    @Test
    void testGetAndSetImagesMap() {
        ExplorationModel explorationModel = new ExplorationModel();
        Map<String, ExplorationImage> mapImmagini = new HashMap<>();
        ExplorationImage img1 = new ExplorationImage();
        img1.setNextScene("Scena 1");
        mapImmagini.put("image1", img1);

        explorationModel.setImagesMap(mapImmagini);

        Map<String, ExplorationImage> risultato = explorationModel.getImagesMap();

        assertEquals(mapImmagini, risultato, "La mappa delle immagini dovrebbe essere correttamente impostata e recuperata.");
    }
}
