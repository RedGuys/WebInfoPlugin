package ru.redguy.webinfo.common.controllers;

import java.util.UUID;

public abstract class AbstractChatController {
    public abstract void sendMessage(String message);

    public abstract void sendMessage(String message, UUID uuid);
}
