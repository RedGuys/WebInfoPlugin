package ru.redguy.webinfo.common.structures;

public class Mod {

    private String name;
    private String version;

    public Mod(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }
}
