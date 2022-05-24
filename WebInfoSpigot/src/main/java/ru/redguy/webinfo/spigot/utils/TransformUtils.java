package ru.redguy.webinfo.spigot.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.redguy.webinfo.common.structures.Location;
import ru.redguy.webinfo.common.structures.Player;
import ru.redguy.webinfo.common.structures.World;
import ru.redguy.webinfo.common.structures.WorldBorder;

import java.util.HashMap;
import java.util.UUID;

public class TransformUtils {
    @Contract("_ -> new")
    public static @NotNull World transform(org.bukkit.@NotNull World world) {
        UUID[] entities = world.getEntities().stream().map(Entity::getUniqueId).toArray(UUID[]::new);
        String[] players = world.getPlayers().stream().map(org.bukkit.entity.Player::getName).toArray(String[]::new);
        HashMap<String, String> gamerules = new HashMap<>();
        for (String rule : world.getGameRules()) {
            gamerules.put(rule, world.getGameRuleValue(rule));
        }
        return new World(world.getName(), entities, players, world.getUID(), transform(world.getSpawnLocation()), world.getTime(), world.getFullTime(), world.hasStorm(), world.getWeatherDuration(), world.isThundering(), world.getThunderDuration(), world.getEnvironment().name(), world.getSeed(), world.getPVP(), world.getMaxHeight(), world.getSeaLevel(), world.getKeepSpawnInMemory(), world.getDifficulty().name(), world.getWorldType().getName(), gamerules, transform(world.getWorldBorder()));
    }

    public static Location transform(org.bukkit.Location location) {
        if (location == null) return null;
        return new Location(location.getBlockX(), location.getBlockY(), location.getBlockZ(), location.getWorld().getName());
    }

    @Contract("_ -> new")
    public static @NotNull WorldBorder transform(org.bukkit.@NotNull WorldBorder worldBorder) {
        return new WorldBorder(worldBorder.getSize(), transform(worldBorder.getCenter()), worldBorder.getDamageBuffer(), worldBorder.getDamageAmount(), worldBorder.getWarningTime(), worldBorder.getWarningDistance());
    }

    @Contract("_ -> new")
    public static @NotNull Player transform(org.bukkit.entity.@NotNull Player player) {
        return new Player(player.getName(), player.getDisplayName(), transform(player.getLocation()), player.getAddress().toString().substring(1), player.isSneaking(), player.isSprinting(), player.getPlayerTime(), player.getExp(), player.getLevel(), player.getTotalExperience(), player.getSaturation(), player.getFoodLevel(), transform(player.getBedSpawnLocation()), player.isFlying(), player.getFlySpeed(), player.getWalkSpeed(), player.getUniqueId(), player.getFirstPlayed(), player.getLastPlayed(), player.hasPlayedBefore(), player.isOp());
    }

    @Contract("_ -> new")
    public static org.bukkit.@NotNull Location transform(@NotNull Location location) {
        return new org.bukkit.Location(Bukkit.getWorld(location.getWorld()), location.getX(), location.getY(), location.getZ());
    }
}
