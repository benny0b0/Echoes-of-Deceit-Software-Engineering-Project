package com.iss.echoesofdeceit.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class NotebookModelTest {

    @Test
    void testCostruttore() {
        NotebookModel notebook = new NotebookModel();
        assertNotNull(notebook.getDiary());
        assertEquals("", notebook.getDiary());
    }

    @Test
    void testSetDiary() {
        NotebookModel notebook = new NotebookModel();
        notebook.setDiary("Testo del diario");
        assertEquals("Testo del diario", notebook.getDiary());
    }

    @Test
    void testGetDiary() {
        NotebookModel notebook = new NotebookModel();
        notebook.setDiary("Contenuto del diario");
        String diary = notebook.getDiary();
        assertEquals("Contenuto del diario", diary);
    }
}
