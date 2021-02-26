package ru.redguy.webinfocommon.utils;

public interface IConfig {
    public void load();
    public void save();

    public boolean getBoolean(String path);
    public int getInt(String path);
    public String getString(String path);

    public void set(String path, Object value);
    public void setIfNull(String path, Object value);
}
