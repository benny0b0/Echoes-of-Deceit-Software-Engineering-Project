package com.iss.echoesofdeceit.controller;

import java.io.IOException;
import java.util.List;

public interface Strategy {
    void execute(List<Object> list) throws IOException;
}
