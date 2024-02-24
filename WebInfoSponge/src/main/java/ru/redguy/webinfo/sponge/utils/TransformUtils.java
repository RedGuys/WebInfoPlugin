package ru.redguy.webinfo.sponge.utils;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.util.Nameable;
import org.spongepowered.api.util.RespawnLocation;
import org.spongepowered.api.world.WorldTypes;
import org.spongepowered.api.world.difficulty.Difficulties;
import org.spongepowered.api.world.gamerule.GameRule;
import org.spongepowered.api.world.server.ServerLocation;
import org.spongepowered.api.world.server.ServerWorld;
import org.spongepowered.api.world.weather.WeatherTypes;
import org.spongepowered.math.vector.Vector2d;
import org.spongepowered.math.vector.Vector3d;
import org.spongepowered.math.vector.Vector3i;
import ru.redguy.webinfo.common.structures.*;

import java.time.Instant;
import java.util.*;

public class TransformUtils {
    public static @NotNull World transform(org.spongepowered.api.world.@NotNull World world) {
        UUID[] entities = world.entities().stream().map(Entity::uniqueId).toArray(UUID[]::new);
        List<String> players = new ArrayList<>();
        for (Object player : world.players()) {
            players.add(((Nameable) player).name());
        }
        ServerWorld sw = null;
        if(world instanceof ServerWorld) {
            sw = (ServerWorld) world;
        }
        return new World(sw != null ? sw.key().asString() : "LocalWorld", entities, players.toArray(new String[0]),
                sw != null ? sw.uniqueId() : UUID.fromString("00000000-0000-0000-0000-000000000000"),
                transform(world.properties().spawnPosition(), sw != null ? sw.key().asString() : "LocalWorld"),
                world.properties().dayTime().asTicks().ticks(), world.properties().gameTime().asTicks().ticks(),
                world.weather().type() == WeatherTypes.RAIN.get(), world.weather().runningDuration().ticks(),
                world.weather().type() == WeatherTypes.THUNDER.get(), world.weather().runningDuration().ticks(),
                WorldTypes.registry().valueKey(world.worldType()).value(), world.seed(),
                sw != null && sw.properties().pvp(), world.height(), world.seaLevel(),
                sw != null && sw.properties().performsSpawnLogic(),
                Difficulties.registry().valueKey(world.difficulty()).value(), world.worldType().asTemplate().key().value(),
                transformGameRules(world.properties().gameRules()), transform(world.properties().worldBorder()));
    }

    public static HashMap<String, String> transformGameRules(Map<GameRule<?>, ?> gameRules) {
        HashMap<String, String> gamerules = new HashMap<>();
        for (Map.Entry<GameRule<?>, ?> entry : gameRules.entrySet()) {
            gamerules.put(entry.getKey().name(),entry.getValue().toString());
        }
        return gamerules;
    }

    public static Location transform(RespawnLocation location) {
        if (location == null) return null;
        return new Location(location.position().x(), location.position().y(), location.position().z(), location.worldKey().value());
    }

    public static Location transform(Vector3i location, String world) {
        return new Location(location.x(), location.y(), location.z(), world);
    }

    @Contract("_ -> new")
    public static @NotNull Location transform(@NotNull Vector3d location) {
        return new Location(location.x(), location.y(), location.z());
    }

    public static Location transform(org.spongepowered.api.world.Location location) {
        ServerWorld sw = null;
        if(location.world() instanceof ServerWorld) {
            sw = (ServerWorld) location.world();
        }
        return new Location(location.x(), location.y(), location.z(), sw != null ? sw.key().asString() : "LocalWorld");
    }

    public static ServerLocation transform(Location location) {
        return ServerLocation.of(Sponge.server().worldManager().world(ResourceKey.of(location.getWorld(), location.getWorld())).get(), new Vector3d(location.getX(), location.getY(), location.getZ()));
    }

    @Contract("_ -> new")
    public static @NotNull WorldBorder transform(org.spongepowered.api.world.border.WorldBorder worldBorder) {
        return new WorldBorder(worldBorder.diameter(), transform(worldBorder.center()), worldBorder.safeZone(), worldBorder.damagePerBlock(), worldBorder.warningTime().getSeconds()*20, worldBorder.warningDistance());
    }

    public static @Nullable Location transform(@NotNull Map<ResourceKey, RespawnLocation> locations) {
        RespawnLocation loc = locations.values().stream().filter(RespawnLocation::isForced).findAny().orElse(null);
        if (loc == null) return null;
        return transform(loc);
    }

    public static Player transform(org.spongepowered.api.entity.living.player.Player player) {
        if (player == null) return null;
        Location bedLocation = transform(player.get(Keys.RESPAWN_LOCATIONS).orElse(new HashMap<>()));
        ServerPlayer sp = null;
        if(player instanceof ServerPlayer) {
            sp = (ServerPlayer) player;
        }
        return new Player(player.name(), PlainTextComponentSerializer.plainText().serialize(player.displayName().get()), transform(player.location()), sp != null ? sp.connection().address().toString().substring(1) : "localhost", player.get(Keys.IS_SNEAKING).orElse(false), player.get(Keys.IS_SPRINTING).orElse(false), player.get(Keys.EXPERIENCE_FROM_START_OF_LEVEL).orElse(0), player.get(Keys.EXPERIENCE_LEVEL).orElse(0), player.get(Keys.EXPERIENCE).orElse(0), player.saturation().get().floatValue(), player.foodLevel().get(), bedLocation, player.get(Keys.IS_FLYING).orElse(false), player.get(Keys.FLYING_SPEED).orElse(.0).floatValue(), player.get(Keys.WALKING_SPEED).orElse(.0).floatValue(), player.uniqueId(), sp != null ? sp.firstJoined().get().get().getEpochSecond() : 0, sp != null ? sp.lastJoined().get().get().getEpochSecond() : Instant.now().getEpochSecond(), sp == null || sp.hasPlayedBefore());
    }

    public static ResourceKey transform(String key) {
        String[] parts = key.split(":");
        return ResourceKey.of(parts[0], parts[1]);
    }

    public static Vec2d transform(Vector2d vec) {
        return new Vec2d(vec.x(), vec.y());
    }
}
