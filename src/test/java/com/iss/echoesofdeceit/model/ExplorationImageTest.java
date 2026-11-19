package com.iss.echoesofdeceit.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ExplorationImageTest {

    @Test
    void testGetAndSetNextTypeOfScene() {
        ExplorationImage explorationImage = new ExplorationImage();
        String tipoProssimaScena = "Scena di investigazione";

        explorationImage.setNextTypeOfScene(tipoProssimaScena);

        String risultato = explorationImage.getNextTypeOfScene();

        assertEquals(tipoProssimaScena, risultato, "Il tipo di prossima scena dovrebbe essere correttamente impostato e recuperato.");
    }

    @Test
    void testGetAndSetCurrent() {
        ExplorationImage explorationImage = new ExplorationImage();
        int current = 5;

        explorationImage.setCurrent(current);

        int risultato = explorationImage.getCurrent();

        assertEquals(current, risultato, "Il valore di current dovrebbe essere correttamente impostato e recuperato.");
    }

    @Test
    void testGetAndSetItem() {
        ExplorationImage explorationImage = new ExplorationImage();
        Item item = new Item();

        explorationImage.setItem(item);

        Item risultato = explorationImage.getItem();

        assertEquals(item, risultato, "L'oggetto item dovrebbe essere correttamente impostato e recuperato.");
    }

    @Test
    void testGetAndSetDiary() {
        ExplorationImage explorationImage = new ExplorationImage();
        String diario = "Questo è il mio diario";

        explorationImage.setDiary(diario);

        String risultato = explorationImage.getDiary();

        assertEquals(diario, risultato, "Il diario dovrebbe essere correttamente impostato e recuperato.");
    }

    @Test
    void testGetAndSetNextScene() {
        ExplorationImage explorationImage = new ExplorationImage();
        String prossimaScena = "Zona 1";

        explorationImage.setNextScene(prossimaScena);

        String risultato = explorationImage.getNextScene();

        assertEquals(prossimaScena, risultato, "La prossima scena dovrebbe essere correttamente impostata e recuperata.");
    }

    @Test
    void testGetAndSetNextNextScene() {
        ExplorationImage explorationImage = new ExplorationImage();
        String prossimaScenaSuccessiva = "Zona";

        explorationImage.setNextNextScene(prossimaScenaSuccessiva);

        String risultato = explorationImage.getNextNextScene();

        assertEquals(prossimaScenaSuccessiva, risultato, "La prossima scena successiva dovrebbe essere correttamente impostata e recuperata.");
    }
}
