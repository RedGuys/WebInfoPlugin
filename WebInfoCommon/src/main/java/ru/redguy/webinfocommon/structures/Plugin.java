package ru.redguy.webinfocommon.structures;

public class Plugin {

    private String name;
    private String version;

    public Plugin(String name, String version) {
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
