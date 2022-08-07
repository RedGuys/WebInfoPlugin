package ru.redguy.webinfo.spigot;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.redguy.miniwebserver.WebServerBuilder;
import ru.redguy.webinfo.common.WebInfoCommon;
import ru.redguy.webinfo.common.controllers.Controllers;
import ru.redguy.webinfo.common.utils.Config;
import ru.redguy.webinfo.common.utils.Logger;
import ru.redguy.webinfo.common.utils.LoggerType;
import ru.redguy.webinfo.spigot.pages.discordsrv.DiscordSRV;
import ru.redguy.webinfo.spigot.utils.*;

public final class WebInfoSpigot extends JavaPlugin {

    private static WebInfoSpigot instance;

    public static WebInfoSpigot getInstance() {
        return instance;
    }

    private boolean sparkAvailable = false;

    @Override
    public void onEnable() {
        instance = this;
        Logger.InjectLogger(new SpigotLogger(getLogger()));
        Config.InjectConfig(new SpigotConfig(getConfig(), getDataFolder()));
        Config.save();
        Controllers.setBasicController(new SpigotInfoUtils());
        Controllers.setWorldsController(new SpigotWorldsController());
        Controllers.setPlayersController(new SpigotPlayersController());
        Controllers.setChatController(new SpigotChatController());
        Controllers.setEntityController(new SpigotEntityController());

        WebServerBuilder webServerBuilder = new WebServerBuilder();

        if (Bukkit.getPluginManager().getPlugin("DiscordSRV") != null) {
            webServerBuilder.addRouter(new DiscordSRV());
        }
        if (Bukkit.getPluginManager().getPlugin("spark") != null) {
            sparkAvailable = true;
        }

        if (Config.getBoolean("modules.webserver")) {
            try {
                WebInfoCommon.server = WebInfoCommon.buildWebServer(webServerBuilder);
                WebInfoCommon.server.pageScan();
                WebInfoCommon.server.startServer(Config.getInt("web.port"));
                Logger.info(LoggerType.Client, "Started web server at " + Config.getInt("web.port"));
            } catch (Exception e) {
                Logger.error(LoggerType.Client, "Error while webserver starting!");
            }
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public boolean isSparkAvailable() {
        return sparkAvailable;
    }
}
