package ru.redguy.webinfocommon.structures;

import org.json.JSONObject;
import ru.redguy.webinfocommon.utils.Jsonable;

public class Location implements Jsonable {
    private final double x;
    private final double y;
    private final double z;

    public Location(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Location(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("x",x);
        json.put("y",y);
        json.put("z",z);
        return json;
    }
}
