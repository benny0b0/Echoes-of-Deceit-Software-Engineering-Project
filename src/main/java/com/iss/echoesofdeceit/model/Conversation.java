package com.iss.echoesofdeceit.model;

public class Conversation {
    private String speaker;
    private String text;
    private int id;
    // Getter e Setter
    public String getSpeaker() {
        return speaker;
    }
    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}