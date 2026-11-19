package com.iss.echoesofdeceit.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class DialogModelTest {

    @Test
    void testGetAndSetInvestigator() {
        DialogModel dialogModel = new DialogModel();
        String expectedInvestigator = "Detective Holmes";

        dialogModel.setInvestigator(expectedInvestigator);
        String actualInvestigator = dialogModel.getInvestigator();

        assertEquals(expectedInvestigator, actualInvestigator, "L'investigatore dovrebbe essere impostato e recuperato correttamente.");
    }

    @Test
    void testGetAndSetInterlocutor() {
        DialogModel dialogModel = new DialogModel();
        String expectedInterlocutor = "Dr. Watson";

        dialogModel.setInterlocutor(expectedInterlocutor);
        String actualInterlocutor = dialogModel.getInterlocutor();

        assertEquals(expectedInterlocutor, actualInterlocutor, "L'interlocutore dovrebbe essere impostato e recuperato correttamente.");
    }

    @Test
    void testGetAndSetConversation() {
        DialogModel dialogModel = new DialogModel();

        List<Conversation> expectedConversations = new ArrayList<>();
        Conversation conversation1 = new Conversation();
        conversation1.setSpeaker("Detective Holmes");
        conversation1.setText("Dove sei stato la scorsa notte?");
        conversation1.setId(1);

        Conversation conversation2 = new Conversation();
        conversation2.setSpeaker("Dr. Watson");
        conversation2.setText("Ero a casa a leggere.");
        conversation2.setId(2);

        expectedConversations.add(conversation1);
        expectedConversations.add(conversation2);

        dialogModel.setConversation(expectedConversations);
        List<Conversation> actualConversations = dialogModel.getConversation();

        assertEquals(expectedConversations, actualConversations, "La lista delle conversazioni dovrebbe essere impostata e recuperata correttamente.");
        assertEquals(2, actualConversations.size(), "La lista delle conversazioni dovrebbe contenere il numero corretto di elementi.");
        assertEquals("Detective Holmes", actualConversations.get(0).getSpeaker(), "Il speaker della prima conversazione dovrebbe corrispondere.");
        assertEquals("Dr. Watson", actualConversations.get(1).getSpeaker(), "Il speaker della seconda conversazione dovrebbe corrispondere.");
    }
}
