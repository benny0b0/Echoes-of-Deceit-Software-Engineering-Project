package com.iss.echoesofdeceit.model;

import java.util.List;

public class DialogModel {
    private String investigator;
    private String interlocutor;
    private List<Conversation> conversation;

    public String getInvestigator() {
        return investigator;
    }
    public void setInvestigator(String investigator) {
        this.investigator = investigator;
    }
    public String getInterlocutor() {
        return interlocutor;
    }
    public void setInterlocutor(String interlocutor) {
        this.interlocutor = interlocutor;
    }
    public List<Conversation> getConversation() {
        return conversation;
    }
    public void setConversation(List<Conversation> conversation) {
        this.conversation = conversation;
    }
}