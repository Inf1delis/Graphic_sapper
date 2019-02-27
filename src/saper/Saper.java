package saper;

import controller.AbstractController;
import controller.ConsoleController;

public class Saper {

    public static void main(String[] args) {
        AbstractController controller = new ConsoleController();
        controller.getClicks();
    }


}