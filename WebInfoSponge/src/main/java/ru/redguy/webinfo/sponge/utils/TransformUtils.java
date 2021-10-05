package ru.redguy.webinfo.sponge.utils;

import com.flowpowered.math.vector.Vector3d;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.value.mutable.MapValue;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.util.RespawnLocation;
import ru.redguy.webinfo.common.structures.Location;
import ru.redguy.webinfo.common.structures.Player;
import ru.redguy.webinfo.common.structures.World;
import ru.redguy.webinfo.common.structures.WorldBorder;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TransformUtils {
    public static World transform(org.spongepowered.api.world.World world) {
        UUID[] entities = world.getEntities().stream().map(Entity::getUniqueId).toArray(UUID[]::new);
        String[] players = world.getPlayers().stream().map(org.spongepowered.api.entity.living.player.Player::getName).toArray(String[]::new);
        String[] gamerules = world.getGameRules().keySet().toArray(new String[0]);
        return new World(world.getName(), entities, players, world.getUniqueId(), transform(world.getSpawnLocation()), world.getProperties().getWorldTime(), world.getProperties().getTotalTime(), world.getProperties().isRaining(), world.getProperties().getRainTime(), world.getProperties().isThundering(), world.getProperties().getThunderTime(), world.getProperties().getDimensionType().getId(), world.getProperties().getSeed(), world.getProperties().isPVPEnabled(), world.getDimension().getHeight(), world.getSeaLevel(), world.getProperties().doesKeepSpawnLoaded(), world.getDifficulty().getId(), world.getDimension().getGeneratorType().getName(), gamerules, transform(world.getWorldBorder()));
    }

    public static Location transform(org.spongepowered.api.world.Location<org.spongepowered.api.world.World> location) {
        if(location == null) return null;
        return new Location(location.getBlockX(), location.getBlockY(), location.getBlockZ(), location.getExtent().getName());
    }

    public static Location transform(Vector3d location) {
        return new Location(location.getX(), location.getY(), location.getZ());
    }

    public static WorldBorder transform(org.spongepowered.api.world.WorldBorder worldBorder) {
        return new WorldBorder(worldBorder.getDiameter(), transform(worldBorder.getCenter()), worldBorder.getDamageThreshold(), worldBorder.getDamageAmount(), worldBorder.getWarningTime(), worldBorder.getWarningDistance());
    }

    public static Location transform(Map<UUID, RespawnLocation> locations) {
        RespawnLocation loc = locations.values().stream().filter(RespawnLocation::isForced).findAny().orElse(null);
        if(loc == null) return null;
        return transform(loc.asLocation().orElse(null));
    }

    public static Player transform(org.spongepowered.api.entity.living.player.Player player) {
        if(player == null) return null;
        Location bedLocation = transform(player.get(Keys.RESPAWN_LOCATIONS).orElse(new HashMap<>()));
        return new Player(player.getName(),player.getDisplayNameData().displayName().get().toPlain(),transform(player.getLocation()),player.getConnection().getAddress().toString().substring(1),player.get(Keys.IS_SNEAKING).orElse(false),player.get(Keys.IS_SPRINTING).orElse(false),player.get(Keys.EXPERIENCE_FROM_START_OF_LEVEL).orElse(0),player.get(Keys.EXPERIENCE_LEVEL).orElse(0),player.get(Keys.TOTAL_EXPERIENCE).orElse(0),player.saturation().get().floatValue(),player.getFoodData().foodLevel().get(),bedLocation,player.get(Keys.IS_FLYING).orElse(false),player.get(Keys.FLYING_SPEED).orElse(.0).floatValue(),player.get(Keys.WALKING_SPEED).orElse(.0).floatValue(),player.getUniqueId(),player.getJoinData().firstPlayed().get().getEpochSecond(),player.getJoinData().lastPlayed().get().getEpochSecond(),player.hasPlayedBefore());
    }

    public static org.spongepowered.api.world.Location<org.spongepowered.api.world.World> transform(Location location) {
        return new org.spongepowered.api.world.Location<>(Sponge.getServer().getWorld(location.getWorld()).get(), location.getX(), location.getY(), location.getZ());
    }
}
