package ru.redguy.webinfo.common.structures;

import java.util.UUID;

public class World {
    protected String name;
    protected UUID[] entities;
    protected String[] players;
    protected UUID uuid;
    protected Location spawnLocation;
    protected long time;
    protected long fullTime;
    protected boolean storm;
    protected int weatherDuration;
    protected boolean thundering;
    protected int thunderDuration;
    protected String environment;
    protected long seed;
    protected boolean pvp;
    protected int maxHeight;
    protected int seaLevel;
    protected boolean keepSpawnInMemory;
    protected String difficulty;
    protected String worldType;
    protected String[] gameRules;
    protected WorldBorder worldBorder;

    public World(String name, UUID[] entities, String[] players, UUID uuid, Location spawnLocation, long time, long fullTime, boolean storm, int weatherDuration, boolean thundering, int thunderDuration, String environment, long seed, boolean pvp, int maxHeight, int seaLevel, boolean keepSpawnInMemory, String difficulty, String worldType, String[] gameRules, WorldBorder worldBorder) {
        this.name = name;
        this.entities = entities;
        this.players = players;
        this.uuid = uuid;
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

    public World(String name, UUID[] entities, String[] players, UUID uuid, Location spawnLocation, long time, long fullTime, boolean storm, int weatherDuration, boolean thundering, int thunderDuration, long seed, int maxHeight, int seaLevel, String difficulty, String worldType, String[] gameRules, WorldBorder worldBorder) {
        this.name = name;
        this.entities = entities;
        this.players = players;
        this.uuid = uuid;
        this.spawnLocation = spawnLocation;
        this.time = time;
        this.fullTime = fullTime;
        this.storm = storm;
        this.weatherDuration = weatherDuration;
        this.thundering = thundering;
        this.thunderDuration = thunderDuration;
        this.seed = seed;
        this.maxHeight = maxHeight;
        this.seaLevel = seaLevel;
        this.difficulty = difficulty;
        this.worldType = worldType;
        this.gameRules = gameRules;
        this.worldBorder = worldBorder;
    }

    public String getName() {
        return name;
    }

    public UUID[] getEntities() {
        return entities;
    }

    public String[] getPlayers() {
        return players;
    }

    public UUID getUuid() {
        return uuid;
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
}
