package ru.redguy.webinfo.sponge;

import com.google.inject.Inject;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Plugin;
import ru.redguy.webinfo.common.WebServer;
import ru.redguy.webinfo.common.controllers.Controllers;
import ru.redguy.webinfo.common.utils.*;
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
    public void onServerStart(GameStartedServerEvent event) throws IOException {
        instance = this;
        Logger.InjectLogger(new SpongeLogger(logger));
        Config.InjectConfig(new SpongeConfig());
        Config.save();
        Controllers.setBasicController(new SpongeInfoUtils());
        Controllers.setWorldsController(new SpongeWorldsController());
        Controllers.setPlayersController(new SpongePlayersController());
        Controllers.setChatController(new SpongeChatController());
        WebServer.getInstance().updateReflection();
        WebServer.getInstance().pageScan();
        WebServer.getInstance().startServer(Config.getInt("web.port"));
    }
}
