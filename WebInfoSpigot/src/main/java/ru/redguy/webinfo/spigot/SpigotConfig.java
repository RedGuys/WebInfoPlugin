package ru.redguy.webinfo.spigot;

import org.bukkit.configuration.file.FileConfiguration;
import ru.redguy.webinfo.common.utils.IConfig;
import ru.redguy.webinfo.common.utils.Logger;
import ru.redguy.webinfo.common.utils.LoggerType;

import java.io.File;
import java.io.IOException;

public class SpigotConfig implements IConfig {

    private FileConfiguration configuration;
    private File dataFolder;

    public SpigotConfig(FileConfiguration configuration, File dataFolder) {
        this.configuration = configuration;
        this.dataFolder = dataFolder;
        load();
    }

    @Override
    public void load() {
        setIfNull("web.port",8080);
        setIfNull("general.lang","ru");
    }

    @Override
    public void save() {
        try {
            configuration.save(new File(dataFolder,"config.yml"));
        } catch (IOException e) {
            Logger.warn(LoggerType.Client,"Error while saving config");
        }
    }

    @Override
    public boolean getBoolean(String path) {
        return configuration.getBoolean(path);
    }

    @Override
    public int getInt(String path) {
        return configuration.getInt(path);
    }

    @Override
    public String getString(String path) {
        return configuration.getString(path);
    }

    @Override
    public void set(String path, Object value) {
        configuration.set(path, value);
    }

    @Override
    public void setIfNull(String path, Object value) {
        if(!configuration.isSet(path)) {
            configuration.set(path, value);
        }
    }
}
