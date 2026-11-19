package com.iss.echoesofdeceit.controller.command;

import com.iss.echoesofdeceit.model.StageModel;

public class ExitGameCommand implements Command {
    @Override
    public void execute() {
        StageModel.getInstance(null).getStage().close();
    }
}
