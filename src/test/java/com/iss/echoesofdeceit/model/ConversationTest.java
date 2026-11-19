package com.iss.echoesofdeceit.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ConversationTest {

    @Test
    void testGetAndSetSpeaker() {
        Conversation conversation = new Conversation();
        String expectedSpeaker = "John Doe";

        conversation.setSpeaker(expectedSpeaker);
        String actualSpeaker = conversation.getSpeaker();

        assertEquals(expectedSpeaker, actualSpeaker, "Speaker corrretto");
    }

    @Test
    void testGetAndSetText() {
        Conversation conversation = new Conversation();
        String expectedText = "Ciao";

        conversation.setText(expectedText);
        String actualText = conversation.getText();

        assertEquals(expectedText, actualText, "test andato bene");
    }

    @Test
    void testGetAndSetId() {
        Conversation conversation = new Conversation();
        int expectedId = 42;

        conversation.setId(expectedId);
        int actualId = conversation.getId();

        assertEquals(expectedId, actualId, "Id corretto");
    }
}
