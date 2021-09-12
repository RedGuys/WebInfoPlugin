package ru.redguy.webinfo.spigot;

import org.bukkit.plugin.java.JavaPlugin;
import ru.redguy.webinfo.common.WebServer;
import ru.redguy.webinfo.common.utils.Config;
import ru.redguy.webinfo.common.utils.InfoUtils;
import ru.redguy.webinfo.common.utils.Logger;
import ru.redguy.webinfo.common.utils.LoggerType;
import ru.redguy.webinfo.spigot.utils.SpigotInfoUtils;
import ru.redguy.webinfo.spigot.utils.SpigotLogger;

import java.io.IOException;

public final class WebInfoSpigot extends JavaPlugin {

    @Override
    public void onEnable() {
        Logger.InjectLogger(new SpigotLogger(getLogger()));
        Config.InjectConfig(new SpigotConfig(getConfig(),getDataFolder()));
        Config.save();
        InfoUtils.InjectInfoUtils(new SpigotInfoUtils());
        try {
            WebServer.getInstance().updateReflection();
            WebServer.getInstance().pageScan();
            WebServer.getInstance().startServer(Config.getInt("web.port"));
            Logger.info(LoggerType.Client, "Started web server at "+Config.getInt("web.port"));
        } catch (IOException e) {
            Logger.error(LoggerType.Client,"Error while webserver starting!");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
