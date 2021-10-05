package ru.redguy.webinfo.common.structures;

import java.util.UUID;

public class Player {
    protected String name;
    protected String displayName;
    protected Location location;
    protected String address;
    protected boolean sneaking;
    protected boolean sprinting;
    protected long playerTime;
    protected float exp;
    protected int level;
    protected int totalExperience;
    protected float saturation;
    protected int foodLevel;
    protected Location bedSpawnLocation;
    protected boolean flying;
    protected float flySpeed;
    protected float walkSpeed;
    protected UUID uuid;
    protected long firstPlayed;
    protected long lastPlayed;
    protected boolean playedBefore;
    protected boolean op;

    public Player(String name, String displayName, Location location, String address, boolean sneaking, boolean sprinting, long playerTime, float exp, int level, int totalExperience, float saturation, int foodLevel, Location bedSpawnLocation, boolean flying, float flySpeed, float walkSpeed, UUID uuid, long firstPlayed, long lastPlayed, boolean playedBefore, boolean op) {
        this.name = name;
        this.displayName = displayName;
        this.location = location;
        this.address = address;
        this.sneaking = sneaking;
        this.sprinting = sprinting;
        this.playerTime = playerTime;
        this.exp = exp;
        this.level = level;
        this.totalExperience = totalExperience;
        this.saturation = saturation;
        this.foodLevel = foodLevel;
        this.bedSpawnLocation = bedSpawnLocation;
        this.flying = flying;
        this.flySpeed = flySpeed;
        this.walkSpeed = walkSpeed;
        this.uuid = uuid;
        this.firstPlayed = firstPlayed;
        this.lastPlayed = lastPlayed;
        this.playedBefore = playedBefore;
        this.op = op;
    }

    public Player(String name, String displayName, Location location, String address, boolean sneaking, boolean sprinting, float exp, int level, int totalExperience, float saturation, int foodLevel, Location bedSpawnLocation, boolean flying, float flySpeed, float walkSpeed, UUID uuid, long firstPlayed, long lastPlayed, boolean playedBefore) {
        this.name = name;
        this.displayName = displayName;
        this.location = location;
        this.address = address;
        this.sneaking = sneaking;
        this.sprinting = sprinting;
        this.exp = exp;
        this.level = level;
        this.totalExperience = totalExperience;
        this.saturation = saturation;
        this.foodLevel = foodLevel;
        this.bedSpawnLocation = bedSpawnLocation;
        this.flying = flying;
        this.flySpeed = flySpeed;
        this.walkSpeed = walkSpeed;
        this.uuid = uuid;
        this.firstPlayed = firstPlayed;
        this.lastPlayed = lastPlayed;
        this.playedBefore = playedBefore;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Location getLocation() {
        return location;
    }

    public String getAddress() {
        return address;
    }

    public boolean isSneaking() {
        return sneaking;
    }

    public boolean isSprinting() {
        return sprinting;
    }

    public long getPlayerTime() {
        return playerTime;
    }

    public float getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
    }

    public int getTotalExperience() {
        return totalExperience;
    }

    public float getSaturation() {
        return saturation;
    }

    public int getFoodLevel() {
        return foodLevel;
    }

    public Location getBedSpawnLocation() {
        return bedSpawnLocation;
    }

    public boolean isFlying() {
        return flying;
    }

    public float getFlySpeed() {
        return flySpeed;
    }

    public float getWalkSpeed() {
        return walkSpeed;
    }

    public UUID getUuid() {
        return uuid;
    }

    public long getFirstPlayed() {
        return firstPlayed;
    }

    public long getLastPlayed() {
        return lastPlayed;
    }

    public boolean isPlayedBefore() {
        return playedBefore;
    }

    public boolean isOp() {
        return op;
    }
}
