package com.iss.echoesofdeceit.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

class ZoneModelTest {

    @Test
    void testSetGetPuzzle() {
        ZoneModel zone = new ZoneModel();
        List<PuzzleModel> puzzles = new ArrayList<>();
        zone.setPuzzle(puzzles);
        assertEquals(puzzles, zone.getPuzzle());
    }

    @Test
    void testSetGetExploration() {
        ZoneModel zone = new ZoneModel();
        ExplorationModel exploration = new ExplorationModel();
        zone.setExploration(exploration);
        assertEquals(exploration, zone.getExploration());
    }

    @Test
    void testSetGetDialogs() {
        ZoneModel zone = new ZoneModel();
        List<DialogModel> dialogs = new ArrayList<>();
        zone.setDialogs(dialogs);
        assertEquals(dialogs, zone.getDialogs());
    }

    @Test
    void testSetGetNumberOfItems() {
        ZoneModel zone = new ZoneModel();
        zone.setNumberOfItems(5);
        assertEquals(5, zone.getNumberOfItems());
    }
}
