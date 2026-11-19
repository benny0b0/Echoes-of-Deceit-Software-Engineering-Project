package com.iss.echoesofdeceit.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class PlayerModelTest {

    @Test
    void testGetInstance() {
        PlayerModel player1 = PlayerModel.getInstance();
        PlayerModel player2 = PlayerModel.getInstance();
        assertSame(player1, player2);
    }

    @Test
    void testSetName() {
        PlayerModel player = PlayerModel.getInstance();
        player.setName("Giocatore di Test");
        assertEquals("Giocatore di Test", player.getName());
    }

    @Test
    void testGetCurrentDistrict() {
        PlayerModel player = PlayerModel.getInstance();
        Map<String, String> district = new HashMap<>();
        district.put("zona1", "Test Zona");
        player.setCurrentDistrict(district);
        assertEquals(district, player.getCurrentDistrict());
    }

    @Test
    void testSetRemainingDays() {
        PlayerModel player = PlayerModel.getInstance();
        player.setRemainingDays(3);
        assertEquals(3, player.getRemainingDays());
    }

    @Test
    void testGetInventory() {
        PlayerModel player = PlayerModel.getInstance();
        InventoryModel inventory = player.getInventory();
        assertNotNull(inventory);
    }

    @Test
    void testGetNotebook() {
        PlayerModel player = PlayerModel.getInstance();
        NotebookModel notebook = player.getNotebook();
        assertNotNull(notebook);
    }

    @Test
    void testSetCompletedPuzzles() {
        PlayerModel player = PlayerModel.getInstance();
        Map<String, Boolean> puzzles = new HashMap<>();
        puzzles.put("puzzle1", true);
        player.setCompletedPuzzles(puzzles);
        assertEquals(puzzles, player.getCompletedPuzzles());
    }

    @Test
    void testToString() {
        PlayerModel player = PlayerModel.getInstance();
        player.setName("Giocatore di Test");
        player.setRemainingDays(3);
        player.setCurrentDistrict(new HashMap<>());
        player.setLocalItemList(new ArrayList<>());
        String expectedString = "\n\nPLAYERMODEL {" +
                "name='Giocatore di Test\n" +
                ", currentDistrict=" + player.getCurrentDistrict() + "\n" +
                ", remainingDays=3\n" +
                ", inventory=" + player.getInventory() + "\n" +
                ", localList= " + player.getLocalItemList() + "\n" +
                ", notebook=" + player.getNotebook() + "\n" +
                ", completedPuzzles=" + player.getCompletedPuzzles() +
                "}\n\n";
        assertEquals(expectedString, player.toString());
    }

    @Test
    void testGetLocalItemList() {
        PlayerModel player = PlayerModel.getInstance();
        player.setLocalItemList(new ArrayList<>());
        assertNotNull(player.getLocalItemList());
    }
}
