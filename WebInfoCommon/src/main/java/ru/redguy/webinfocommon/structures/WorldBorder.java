package ru.redguy.webinfocommon.structures;

import org.json.JSONObject;
import ru.redguy.webinfocommon.utils.Jsonable;

public class WorldBorder implements Jsonable {
    private final double size;
    private final Location center;
    private final double damageBuffer;
    private final double damageAmount;
    private final int warningTime;
    private final int warningDistance;

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

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("size",size);
        json.put("center",center.toJSONObject());
        json.put("damageBuffer",damageBuffer);
        json.put("damageAmount",damageAmount);
        json.put("warningTime",warningTime);
        json.put("warningDistance",warningDistance);
        return json;
    }
}
