package com.iss.echoesofdeceit.model;

public class ExplorationImage {
    private String nextTypeOfScene;
    private String nextScene;
    private String nextNextScene="Zone";
    private int current;
    private Item item;
    private String diary;

    // Getter e setter
    public String getNextTypeOfScene() {
        return this.nextTypeOfScene;
    }

    public void setNextTypeOfScene(String nextTypeOfScene) {
        this.nextTypeOfScene = nextTypeOfScene;
    }

    public int getCurrent() {
        return this.current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getDiary() {
        return diary;
    }

    public void setDiary(String diary) {
        this.diary = diary;
    }

    public String getNextScene() {
        return nextScene;
    }

    public void setNextScene(String nextScene) {
        this.nextScene = nextScene;
    }


    public String getNextNextScene() {
        return nextNextScene;
    }

    public void setNextNextScene(String nextNextScene) {
        this.nextNextScene = nextNextScene;
    }
}
