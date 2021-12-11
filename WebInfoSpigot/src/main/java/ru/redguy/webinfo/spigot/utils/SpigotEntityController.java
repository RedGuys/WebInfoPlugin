package ru.redguy.webinfo.spigot.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import ru.redguy.webinfo.common.controllers.AbstractEntityController;
import ru.redguy.webinfo.common.structures.Location;
import ru.redguy.webinfo.spigot.WebInfoSpigot;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class SpigotEntityController extends AbstractEntityController {
    @Override
    public CompletableFuture<UUID> spawnEntity(String type, Location location) {
        CompletableFuture<UUID> cf = new CompletableFuture<>();
        Bukkit.getScheduler().runTask(WebInfoSpigot.getInstance(),() -> {
            cf.complete(Bukkit.getWorld(location.getWorld()).spawnEntity(TransformUtils.transform(location), EntityType.fromName(type)).getUniqueId());
        });
        return cf;
    }
}
