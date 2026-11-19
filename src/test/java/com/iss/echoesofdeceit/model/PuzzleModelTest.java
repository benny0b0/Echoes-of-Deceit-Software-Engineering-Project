package com.iss.echoesofdeceit.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.iss.echoesofdeceit.controller.PuzzleComposite;

class PuzzleModelTest {

    @Test
    void testSetId() {
        PuzzleModel puzzle = new PuzzleModel();
        puzzle.setId(1);
        assertEquals(1, puzzle.getId());
    }

    @Test
    void testSetTitle() {
        PuzzleModel puzzle = new PuzzleModel();
        puzzle.setTitle("Titolo del Puzzle");
        assertEquals("Titolo del Puzzle", puzzle.getTitle());
    }

    @Test
    void testSetDescription() {
        PuzzleModel puzzle = new PuzzleModel();
        puzzle.setDescription("Descrizione del Puzzle");
        assertEquals("Descrizione del Puzzle", puzzle.getDescription());
    }

    @Test
    void testSetSolution() {
        PuzzleModel puzzle = new PuzzleModel();
        puzzle.setSolution("Soluzione");
        assertEquals("Soluzione", puzzle.getSolution());
    }

    @Test
    void testSetHint() {
        PuzzleModel puzzle = new PuzzleModel();
        puzzle.setHint("Suggerimento");
        assertEquals("Suggerimento", puzzle.getHint());
    }

    @Test
    void testSetStackpane() {
        PuzzleModel puzzle = new PuzzleModel();
        PuzzleComposite stackpane = new PuzzleComposite();
        puzzle.setStackpane(stackpane);
        assertEquals(stackpane, puzzle.getStackpane());
    }

}
