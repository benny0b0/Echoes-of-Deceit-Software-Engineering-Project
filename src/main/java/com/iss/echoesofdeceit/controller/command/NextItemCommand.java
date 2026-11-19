package com.iss.echoesofdeceit.controller.command;

import com.iss.echoesofdeceit.controller.ZoneController;


public class NextItemCommand implements Command{
    private ZoneController zoneController;

    public NextItemCommand(ZoneController zoneController){
        this.zoneController = zoneController;
    }

    @Override
    public void execute() {
        zoneController.setCurrentPosition(zoneController.getCurrentPosition() + 1);
        if(zoneController.getCurrentPosition() >= zoneController.getImageList().size()){
            zoneController.setCurrentPosition(0);
        }
        zoneController.loadCurrentBackground();
    }
}
