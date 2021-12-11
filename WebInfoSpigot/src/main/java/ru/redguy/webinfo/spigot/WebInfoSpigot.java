package ru.redguy.webinfo.spigot;

import org.bukkit.plugin.java.JavaPlugin;
import ru.redguy.webinfo.common.WebServer;
import ru.redguy.webinfo.common.controllers.Controllers;
import ru.redguy.webinfo.common.utils.*;
import ru.redguy.webinfo.spigot.utils.*;

import java.io.IOException;

public final class WebInfoSpigot extends JavaPlugin {

    private static WebInfoSpigot instance;

    public static WebInfoSpigot getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        Logger.InjectLogger(new SpigotLogger(getLogger()));
        Config.InjectConfig(new SpigotConfig(getConfig(),getDataFolder()));
        Config.save();
        Controllers.setBasicController(new SpigotInfoUtils());
        Controllers.setWorldsController(new SpigotWorldsController());
        Controllers.setPlayersController(new SpigotPlayersController());
        Controllers.setChatController(new SpigotChatController());
        Controllers.setEntityController(new SpigotEntityController());
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
