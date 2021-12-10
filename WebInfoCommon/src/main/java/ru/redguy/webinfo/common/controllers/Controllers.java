package ru.redguy.webinfo.common.controllers;

public class Controllers {
    private static AbstractPlayersController playersController;
    private static AbstractWorldsController worldsController;
    private static AbstractBasicController basicController;
    private static AbstractChatController chatController;

    public static AbstractPlayersController getPlayersController() {
        return playersController;
    }

    public static void setPlayersController(AbstractPlayersController playersController) {
        Controllers.playersController = playersController;
    }

    public static AbstractWorldsController getWorldsController() {
        return worldsController;
    }

    public static void setWorldsController(AbstractWorldsController worldsController) {
        Controllers.worldsController = worldsController;
    }

    public static AbstractBasicController getBasicController() {
        return basicController;
    }

    public static void setBasicController(AbstractBasicController basicController) {
        Controllers.basicController = basicController;
    }

    public static AbstractChatController getChatController() {
        return chatController;
    }

    public static void setChatController(AbstractChatController chatController) {
        Controllers.chatController = chatController;
    }
}
