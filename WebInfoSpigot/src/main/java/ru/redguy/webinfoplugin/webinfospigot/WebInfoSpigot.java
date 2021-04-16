package ru.redguy.webinfoplugin.webinfospigot;

import org.bukkit.plugin.java.JavaPlugin;
import ru.redguy.webinfocommon.UsersConfig;
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
        Language.initLanguage(getDataFolder().getAbsolutePath());
        Language.save();
        BasePlaceholders.registerBase();
        UsersConfig.loadUsers(getDataFolder().getAbsolutePath());
        InfoUtils.InjectInfoUtils(new SpigotInfoUtils());
        Logger.info(LoggerType.Client, String.valueOf(Config.getInt("web.port")));
        try {
            webServer = new WebServer(Config.getInt("web.port"));
        } catch (IOException e) {
            Logger.error(LoggerType.Client,"Error while webserver starting!");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
