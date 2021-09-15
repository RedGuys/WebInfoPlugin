package ru.redguy.webinfo.common.utils;

public class WorldsController {
    private static AbstractWorldsController controller;

    public static void Inject(AbstractWorldsController controller) {
        WorldsController.controller = controller;
    }
    public static AbstractWorldsController getInstance() {return controller;}
}
