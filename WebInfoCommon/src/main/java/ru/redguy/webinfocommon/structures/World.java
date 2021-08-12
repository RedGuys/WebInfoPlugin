package ru.redguy.webinfocommon.structures;

import org.json.JSONObject;
import ru.redguy.webinfocommon.utils.Jsonable;

import java.util.UUID;

public class World implements Jsonable {
    private final String name;
    private final String[] loadedChunks;
    private final UUID[] entities;
    private final String[] players;
    private final UUID uid;
    private final Location spawnLocation;
    private final long time;
    private final long fullTime;
    private final boolean storm;
    private final int weatherDuration;
    private final boolean thundering;
    private final int thunderDuration;
    private final String environment;
    private final long seed;
    private final boolean pvp;
    private final int maxHeight;
    private final int seaLevel;
    private final boolean keepSpawnInMemory;
    private final String difficulty;
    private final String worldType;
    private final String[] gameRules;
    private final WorldBorder worldBorder;

    public World(String name, String[] loadedChunks, UUID[] entities, String[] players, UUID uid, Location spawnLocation, long time, long fullTime, boolean storm, int weatherDuration, boolean thundering, int thunderDuration, String environment, long seed, boolean pvp, int maxHeight, int seaLevel, boolean keepSpawnInMemory, String difficulty, String worldType, String[] gameRules, WorldBorder worldBorder) {
        this.name = name;
        this.loadedChunks = loadedChunks;
        this.entities = entities;
        this.players = players;
        this.uid = uid;
        this.spawnLocation = spawnLocation;
        this.time = time;
        this.fullTime = fullTime;
        this.storm = storm;
        this.weatherDuration = weatherDuration;
        this.thundering = thundering;
        this.thunderDuration = thunderDuration;
        this.environment = environment;
        this.seed = seed;
        this.pvp = pvp;
        this.maxHeight = maxHeight;
        this.seaLevel = seaLevel;
        this.keepSpawnInMemory = keepSpawnInMemory;
        this.difficulty = difficulty;
        this.worldType = worldType;
        this.gameRules = gameRules;
        this.worldBorder = worldBorder;
    }

    public String getName() {
        return name;
    }

    public String[] getLoadedChunks() {
        return loadedChunks;
    }

    public UUID[] getEntities() {
        return entities;
    }

    public String[] getPlayers() {
        return players;
    }

    public UUID getUid() {
        return uid;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public long getTime() {
        return time;
    }

    public long getFullTime() {
        return fullTime;
    }

    public boolean isStorm() {
        return storm;
    }

    public int getWeatherDuration() {
        return weatherDuration;
    }

    public boolean isThundering() {
        return thundering;
    }

    public int getThunderDuration() {
        return thunderDuration;
    }

    public String getEnvironment() {
        return environment;
    }

    public long getSeed() {
        return seed;
    }

    public boolean isPvp() {
        return pvp;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getSeaLevel() {
        return seaLevel;
    }

    public boolean isKeepSpawnInMemory() {
        return keepSpawnInMemory;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getWorldType() {
        return worldType;
    }

    public String[] getGameRules() {
        return gameRules;
    }

    public WorldBorder getWorldBorder() {
        return worldBorder;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("name",name);
        json.put("loadedChunks",loadedChunks);
        json.put("entities",entities);
        json.put("players",players);
        json.put("uid",uid.toString());
        json.put("spawnLocation",spawnLocation.toJSONObject());
        json.put("time",time);
        json.put("fullTime",fullTime);
        json.put("storm",storm);
        json.put("weatherDuration",weatherDuration);
        json.put("environment",environment);
        json.put("seed",seed);
        json.put("pvp",pvp);
        json.put("maxHeight",maxHeight);
        json.put("seaLevel",seaLevel);
        json.put("keepSpawnInMemory",keepSpawnInMemory);
        json.put("difficulty",difficulty);
        json.put("worldType",worldType);
        json.put("gameRules",gameRules);
        json.put("worldBorder",worldBorder.toJSONObject());
        return json;
    }
}
