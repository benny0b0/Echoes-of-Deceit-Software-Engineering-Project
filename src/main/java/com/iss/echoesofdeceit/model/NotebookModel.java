package com.iss.echoesofdeceit.model;

import java.util.HashMap;
import java.util.Map;

public class NotebookModel {
    private String diary;

    public NotebookModel() {
        this.diary = "";  // Impostiamo un valore predefinito per Diary (vuoto)
    }

    // Getter e Setter per thiefCharacteristics

    // Getter e Setter per Diary
    public String getDiary() {
        return diary;
    }

    public void setDiary(String diary) {
        this.diary = diary;
    }
}
