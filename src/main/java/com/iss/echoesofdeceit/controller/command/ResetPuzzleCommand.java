package com.iss.echoesofdeceit.controller.command;

import com.iss.echoesofdeceit.controller.PuzzleController;
import com.iss.echoesofdeceit.controller.PuzzleElement;
import com.iss.echoesofdeceit.model.DistrictModel;
import com.iss.echoesofdeceit.model.GameModel;

public class ResetPuzzleCommand implements Command{
    @Override
    public void execute() {
        GameModel.getInstance().setGameScene("Puzzle");
        PuzzleElement.setIndex(1);
        PuzzleElement.setTextAreaString("");

    }
}
