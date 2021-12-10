package ru.redguy.webinfo.spigot.utils;

import org.bukkit.Bukkit;
import ru.redguy.webinfo.common.controllers.AbstractWorldsController;
import ru.redguy.webinfo.common.structures.ActionResult;

import java.util.concurrent.CompletableFuture;

public class SpigotWorldsController extends AbstractWorldsController {
    @Override
    public boolean isWorldExist(String name) {
        return Bukkit.getWorld(name) != null;
    }

    @Override
    public CompletableFuture<ActionResult> unloadWorld(String name, boolean save) {
        Bukkit.unloadWorld(name, save);
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        res.complete(new ActionResult(true));
        return res;
    }
}
