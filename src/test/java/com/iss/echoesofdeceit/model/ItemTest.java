package com.iss.echoesofdeceit.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    private Item item1;
    private Item item2;

    @BeforeEach
    void setUp() {
        item1 = new Item();
        item1.setName("Spada");
        item1.setDescription("Una lama affilata");
        item1.setImage("spada.png");

        item2 = new Item();
        item2.setName("Spada");
        item2.setDescription("Una lama affilata, leggermente arrugginita");
        item2.setImage("spada_arrugginita.png");
    }

    @Test
    void testGettersAndSetters() {
        Item item = new Item();
        item.setName("Scudo");
        item.setDescription("Uno scudo robusto");
        item.setImage("scudo.png");

        assertEquals("Scudo", item.getName());
        assertEquals("Uno scudo robusto", item.getDescription());
        assertEquals("scudo.png", item.getImage());
    }

    @Test
    void testHashCode() {
        assertEquals(item1.hashCode(), item2.hashCode(), "Gli oggetti con lo stesso nome dovrebbero avere lo stesso codice hash");
        item2.setName("Ascia");
        assertNotEquals(item1.hashCode(), item2.hashCode(), "Gli oggetti con nomi diversi dovrebbero avere codici hash diversi");
    }

    @Test
    void testToString() {
        String expected = "{name='Spada', description='Una lama affilata', image='spada.png'}";
        assertEquals(expected, item1.toString(), "toString dovrebbe produrre il risultato corretto");
    }
}
