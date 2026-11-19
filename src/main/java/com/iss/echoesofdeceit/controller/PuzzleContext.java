package com.iss.echoesofdeceit.controller;

import java.io.IOException;
import java.util.List;

public class PuzzleContext {
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
    public void executeStrategy(List<Object> list) throws IOException {
       this.strategy.execute(list);
    }
}
