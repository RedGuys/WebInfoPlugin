package ru.redguy.webinfo.sponge;

import com.google.inject.Inject;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import ru.redguy.webinfo.common.WebServer;
import ru.redguy.webinfo.common.WebSocketController;
import ru.redguy.webinfo.common.controllers.Controllers;
import ru.redguy.webinfo.common.utils.Config;
import ru.redguy.webinfo.common.utils.Logger;
import ru.redguy.webinfo.common.utils.LoggerType;
import ru.redguy.webinfo.sponge.utils.*;

import java.io.IOException;

@Plugin(
        id = "webinfosponge",
        name = "WebInfoSponge",
        description = ".",
        url = "https://redguy.ru",
        authors = {
                "RedGuy"
        }
)
public class WebInfoSponge {

    @Inject
    private org.slf4j.Logger logger;

    public static WebInfoSponge instance;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        instance = this;
        Logger.InjectLogger(new SpongeLogger(logger));
        Config.InjectConfig(new SpongeConfig());
        Config.save();
        Controllers.setBasicController(new SpongeInfoUtils());
        Controllers.setWorldsController(new SpongeWorldsController());
        Controllers.setPlayersController(new SpongePlayersController());
        Controllers.setChatController(new SpongeChatController());
        Controllers.setEntityController(new SpongeEntityController());

        if (Config.getBoolean("modules.socket")) {
            try {
                WebSocketController.getInstance().updateReflection();
                WebSocketController.getInstance().pageScan();
                WebSocketController.getInstance().connect(Config.getString("socket.path"), Config.getString("socket.key"));
                Logger.info(LoggerType.Client, "Started socket");
            } catch (Exception e) {
                Logger.error(LoggerType.Client, "Error while socket starting!");
            }
        }

        if (Config.getBoolean("modules.webserver")) {
            try {
                WebServer.getInstance().updateReflection();
                WebServer.getInstance().pageScan();
                WebServer.getInstance().startServer(Config.getInt("web.port"));
                Logger.info(LoggerType.Client, "Started web server at " + Config.getInt("web.port"));
            } catch (Exception e) {
                Logger.error(LoggerType.Client, "Error while webserver starting!");
            }
        }
    }
}
