package ru.redguy.webinfo.spigot.utils;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import ru.redguy.webinfo.common.structures.Location;
import ru.redguy.webinfo.common.structures.World;
import ru.redguy.webinfo.common.structures.WorldBorder;

import java.util.UUID;

public class TransformUtils {
    public static World transform(org.bukkit.World world) {
        UUID[] entities = world.getEntities().stream().map(Entity::getUniqueId).toArray(UUID[]::new);
        String[] players = world.getPlayers().stream().map(Player::getName).toArray(String[]::new);
        return new World(world.getName(), entities, players, world.getUID(), transform(world.getSpawnLocation()), world.getTime(), world.getFullTime(), world.hasStorm(), world.getWeatherDuration(), world.isThundering(), world.getThunderDuration(), world.getEnvironment().name(), world.getSeed(), world.getPVP(), world.getMaxHeight(), world.getSeaLevel(), world.getKeepSpawnInMemory(), world.getDifficulty().name(), world.getWorldType().getName(), world.getGameRules(), transform(world.getWorldBorder()));
    }

    public static Location transform(org.bukkit.Location location) {
        return new Location(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    public static WorldBorder transform(org.bukkit.WorldBorder worldBorder) {
        return new WorldBorder(worldBorder.getSize(), transform(worldBorder.getCenter()), worldBorder.getDamageBuffer(), worldBorder.getDamageAmount(), worldBorder.getWarningTime(), worldBorder.getWarningDistance());
    }
}
