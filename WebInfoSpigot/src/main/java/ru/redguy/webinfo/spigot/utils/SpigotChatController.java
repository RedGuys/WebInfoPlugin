package ru.redguy.webinfo.spigot.utils;

import org.bukkit.Bukkit;
import ru.redguy.webinfo.common.utils.AbstractChatController;

import java.util.UUID;

public class SpigotChatController extends AbstractChatController {
    @Override
    public void sendMessage(String message) {
        Bukkit.broadcastMessage(message);
    }

    @Override
    public void sendMessage(String message, UUID uuid) {
        Bukkit.getPlayer(uuid).sendMessage(message);
    }
}
