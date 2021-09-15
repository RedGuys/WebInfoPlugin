package ru.redguy.webinfo.spigot.utils;

import org.bukkit.Bukkit;
import ru.redguy.webinfo.common.utils.AbstractWorldsController;

public class SpigotWorldsController extends AbstractWorldsController {
    @Override
    public boolean isWorldExist(String name) {
        return Bukkit.getWorld(name) != null;
    }

    @Override
    public void unloadWorld(String name, boolean save) {
        Bukkit.unloadWorld(name, save);
    }
}
