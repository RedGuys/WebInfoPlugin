package ru.redguy.webinfo.common.structures;

public class Vec2d {
    protected double x;
    protected double z;

    public Vec2d(double x, double z) {
        this.x = x;
        this.z = z;
    }

    public Vec2d(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public Vec2d(String s) {
        String[] a = s.split(";");
        this.x = Double.parseDouble(a[0]);
        this.z = Double.parseDouble(a[1]);
    }

    public double getX() {
        return x;
    }

    public double getZ() {
        return z;
    }
}
