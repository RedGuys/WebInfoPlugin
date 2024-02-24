package ru.redguy.webinfo.common.structures;

public class WorldBorder {
    protected double size;
    protected Vec2d center;
    protected double damageBuffer;
    protected double damageAmount;
    protected long warningTime;
    protected int warningDistance;

    public WorldBorder(double size, Vec2d center, double damageBuffer, double damageAmount, long warningTime, int warningDistance) {
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

    public Vec2d getCenter() {
        return center;
    }

    public double getDamageBuffer() {
        return damageBuffer;
    }

    public double getDamageAmount() {
        return damageAmount;
    }

    public long getWarningTime() {
        return warningTime;
    }

    public int getWarningDistance() {
        return warningDistance;
    }
}
