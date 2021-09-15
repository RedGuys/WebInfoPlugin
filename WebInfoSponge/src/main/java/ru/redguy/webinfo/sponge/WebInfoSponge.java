package ru.redguy.webinfo.sponge;

import com.google.inject.Inject;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Plugin;
import ru.redguy.webinfo.common.WebServer;
import ru.redguy.webinfo.common.utils.Config;
import ru.redguy.webinfo.common.utils.InfoUtils;
import ru.redguy.webinfo.common.utils.Logger;
import ru.redguy.webinfo.common.utils.WorldsController;
import ru.redguy.webinfo.sponge.utils.SpongeInfoUtils;
import ru.redguy.webinfo.sponge.utils.SpongeLogger;
import ru.redguy.webinfo.sponge.utils.SpongeWorldsController;

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
        InfoUtils.InjectInfoUtils(new SpongeInfoUtils());
        WorldsController.Inject(new SpongeWorldsController());
        WebServer.getInstance().updateReflection();
        WebServer.getInstance().pageScan();
        WebServer.getInstance().startServer(Config.getInt("web.port"));
    }
}
