package ru.redguy.webinfo.spigot.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import ru.redguy.webinfo.common.structures.Location;
import ru.redguy.webinfo.common.structures.Player;
import ru.redguy.webinfo.common.structures.World;
import ru.redguy.webinfo.common.structures.WorldBorder;

import java.util.UUID;

public class TransformUtils {
    public static World transform(org.bukkit.World world) {
        UUID[] entities = world.getEntities().stream().map(Entity::getUniqueId).toArray(UUID[]::new);
        String[] players = world.getPlayers().stream().map(org.bukkit.entity.Player::getName).toArray(String[]::new);
        return new World(world.getName(), entities, players, world.getUID(), transform(world.getSpawnLocation()), world.getTime(), world.getFullTime(), world.hasStorm(), world.getWeatherDuration(), world.isThundering(), world.getThunderDuration(), world.getEnvironment().name(), world.getSeed(), world.getPVP(), world.getMaxHeight(), world.getSeaLevel(), world.getKeepSpawnInMemory(), world.getDifficulty().name(), world.getWorldType().getName(), world.getGameRules(), transform(world.getWorldBorder()));
    }

    public static Location transform(org.bukkit.Location location) {
        if(location == null) return null;
        return new Location(location.getBlockX(), location.getBlockY(), location.getBlockZ(), location.getWorld().getName());
    }

    public static WorldBorder transform(org.bukkit.WorldBorder worldBorder) {
        return new WorldBorder(worldBorder.getSize(), transform(worldBorder.getCenter()), worldBorder.getDamageBuffer(), worldBorder.getDamageAmount(), worldBorder.getWarningTime(), worldBorder.getWarningDistance());
    }

    public static Player transform(org.bukkit.entity.Player player) {
        return new Player(player.getName(),player.getDisplayName(),transform(player.getLocation()),player.getAddress().toString().substring(1),player.isSneaking(),player.isSprinting(),player.getPlayerTime(),player.getExp(),player.getLevel(),player.getTotalExperience(),player.getSaturation(),player.getFoodLevel(),transform(player.getBedSpawnLocation()),player.isFlying(),player.getFlySpeed(),player.getWalkSpeed(),player.getUniqueId(),player.getFirstPlayed(),player.getLastPlayed(),player.hasPlayedBefore(),player.isOp());
    }

    public static org.bukkit.Location transform(Location location) {
        return new org.bukkit.Location(Bukkit.getWorld(location.getWorld()),location.getX(),location.getY(),location.getZ());
    }
}
