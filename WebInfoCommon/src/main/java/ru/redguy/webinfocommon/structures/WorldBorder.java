package ru.redguy.webinfocommon.structures;

public class WorldBorder {
    protected double size;
    protected Location center;
    protected double damageBuffer;
    protected double damageAmount;
    protected int warningTime;
    protected int warningDistance;

    public WorldBorder(double size, Location center, double damageBuffer, double damageAmount, int warningTime, int warningDistance) {
        this.size = size;
        this.center = center;
        this.damageBuffer = damageBuffer;
        this.damageAmount = damageAmount;
        this.warningTime = warningTime;
        this.warningDistance = warningDistance;
    }

    public double getSize() {
        return size;
    }

    public Location getCenter() {
        return center;
    }

    public double getDamageBuffer() {
        return damageBuffer;
    }

    public double getDamageAmount() {
        return damageAmount;
    }

    public int getWarningTime() {
        return warningTime;
    }

    public int getWarningDistance() {
        return warningDistance;
    }
}
