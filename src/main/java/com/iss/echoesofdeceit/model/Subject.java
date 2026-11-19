package com.iss.echoesofdeceit.model;

import com.iss.echoesofdeceit.controller.Observer;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserver();
}
