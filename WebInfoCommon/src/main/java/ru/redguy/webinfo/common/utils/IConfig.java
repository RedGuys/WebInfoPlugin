package ru.redguy.webinfo.common.utils;

public interface IConfig {
    void load();

    void save();

    boolean getBoolean(String path);

    int getInt(String path);

    String getString(String path);

    void set(String path, Object value);

    void setIfNull(String path, Object value);
}
