package ru.redguy.webinfo.sponge;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.serialize.SerializationException;
import ru.redguy.webinfo.common.utils.IConfig;
import ru.redguy.webinfo.common.utils.Logger;
import ru.redguy.webinfo.common.utils.LoggerType;

import java.io.IOException;
import java.nio.file.Paths;

public class SpongeConfig implements IConfig {

    private static HoconConfigurationLoader configurationLoader;
    private static ConfigurationNode rootNode;

    public SpongeConfig() {
        configurationLoader = HoconConfigurationLoader.builder().path(Paths.get("config", "WebInfo", "config.conf")).build();
        load();
    }

    @Override
    public void load() {
        try {
            rootNode = configurationLoader.load();
        } catch (IOException e) {
            rootNode = configurationLoader.createNode();
        }

        setIfNull("web.port", 8080);
        setIfNull("general.lang", "ru");
        setIfNull("modules.socket", false);
        setIfNull("modules.webserver", true);
        setIfNull("socket.path", "ws://localhost:8999");
        setIfNull("socket.key", "test");
    }

    @Override
    public void save() {
        try {
            configurationLoader.save(rootNode);
        } catch (IOException e) {
            Logger.error(LoggerType.Client, "Something went wrong at config saving");
        }
    }

    private ConfigurationNode getNode(ConfigurationNode rootNode, @NotNull String path) {
        ConfigurationNode node = rootNode;
        for (String s : path.split("\\.")) {
            node = node.node(s);
        }
        return node;
    }

    @Override
    public boolean getBoolean(String path) {
        return getNode(rootNode, path).getBoolean();
    }

    @Override
    public int getInt(String path) {
        return getNode(rootNode, path).getInt();
    }

    @Override
    public String getString(String path) {
        return getNode(rootNode, path).getString();
    }

    @Override
    public void set(String path, Object value) {
        try {
            getNode(rootNode, path).set(value);
        } catch (SerializationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setIfNull(String path, Object value) {
        ConfigurationNode node = getNode(rootNode, path);
        if (node.isNull()) {
            try {
                node.set(value);
            } catch (SerializationException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
