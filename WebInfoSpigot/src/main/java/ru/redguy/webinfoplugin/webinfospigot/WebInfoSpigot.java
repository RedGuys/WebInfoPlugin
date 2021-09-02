package ru.redguy.webinfoplugin.webinfospigot;

import org.bukkit.plugin.java.JavaPlugin;
import ru.redguy.webinfocommon.WebServer;
import ru.redguy.webinfocommon.utils.*;
import ru.redguy.webinfoplugin.webinfospigot.utils.SpigotInfoUtils;
import ru.redguy.webinfoplugin.webinfospigot.utils.SpigotLogger;

import java.io.IOException;

public final class WebInfoSpigot extends JavaPlugin {

    private WebServer webServer;

    @Override
    public void onEnable() {
        Logger.InjectLogger(new SpigotLogger(getLogger()));
        Config.InjectConfig(new SpigotConfig(getConfig(),getDataFolder()));
        Config.save();
        InfoUtils.InjectInfoUtils(new SpigotInfoUtils());
        try {
            webServer = new WebServer(Config.getInt("web.port"));
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
