package controller;

import model.Field;

public interface ControllerInterface {
    void getClicks();
    int getWide();
    int getLength();
    int getBombs();
    Field initField();
}
