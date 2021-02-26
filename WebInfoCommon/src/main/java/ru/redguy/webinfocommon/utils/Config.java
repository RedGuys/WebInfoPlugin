package ru.redguy.webinfocommon.utils;

public class Config {
    private static IConfig config;

    public static void InjectConfig(IConfig config) {
        Config.config = config;
    }

    public static boolean getBoolean(String path) {
        return config.getBoolean(path);
    }

    public static int getInt(String path) {
        return config.getInt(path);
    }

    public static String getString(String path) {
        return config.getString(path);
    }

    public static void save() {
        config.save();
    }

    public static void load() {
        config.load();
    }
}
