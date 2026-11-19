package com.iss.echoesofdeceit.controller.command;

import com.iss.echoesofdeceit.controller.ZoneController;


public class BackItemCommand implements Command{
    private ZoneController zoneController;

    public BackItemCommand (ZoneController zoneController){
        this.zoneController = zoneController;
    }

    @Override
    public void execute() {
        zoneController.setCurrentPosition(zoneController.getCurrentPosition() - 1);
        if(zoneController.getCurrentPosition() < 0){
            zoneController.setCurrentPosition(zoneController.getImageList().size() - 1);
        }
        zoneController.loadCurrentBackground();
    }
}
