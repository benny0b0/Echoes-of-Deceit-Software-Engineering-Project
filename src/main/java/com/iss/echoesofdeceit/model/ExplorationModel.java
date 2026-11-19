package com.iss.echoesofdeceit.model;

import java.util.List;
import java.util.Map;

public class ExplorationModel {
    private String roomName;
    private List<String> imageList;
    private Map<String, ExplorationImage> imagesMap;

    //Getter e setter
    public String getRoomName() {
        return roomName;
    }
    public void setRoomName(String roomName){
        this.roomName = roomName;
    }

    public Map<String, ExplorationImage> getImagesMap() {
        return this.imagesMap;
    }

    public void setImagesMap(Map<String, ExplorationImage> imagesMap) {
        this.imagesMap = imagesMap;
    }

    public List<String> getImageList() {
        return this.imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }
}
