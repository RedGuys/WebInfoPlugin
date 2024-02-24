package ru.redguy.webinfo.sponge;

import com.google.inject.Inject;
import org.spongepowered.api.Server;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.lifecycle.StartedEngineEvent;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.builtin.jvm.Plugin;
import ru.redguy.miniwebserver.WebServerBuilder;
import ru.redguy.webinfo.common.WebInfoCommon;
import ru.redguy.webinfo.common.controllers.Controllers;
import ru.redguy.webinfo.common.utils.Config;
import ru.redguy.webinfo.common.utils.Logger;
import ru.redguy.webinfo.common.utils.LoggerType;
import ru.redguy.webinfo.sponge.utils.*;

@Plugin("webinfosponge")
public class WebInfoSponge {

    private final PluginContainer container;
    private final org.apache.logging.log4j.Logger logger;

    @Inject
    public WebInfoSponge(final PluginContainer container, final org.apache.logging.log4j.Logger logger) {
        this.container = container;
        this.logger = logger;
    }

    public static WebInfoSponge instance;

    @Listener
    public void onServerStart(StartedEngineEvent<Server> event) {
        instance = this;
        Logger.InjectLogger(new SpongeLogger(logger));
        Config.InjectConfig(new SpongeConfig());
        Config.save();
        Controllers.setBasicController(new SpongeInfoUtils());
        Controllers.setWorldsController(new SpongeWorldsController());
        Controllers.setPlayersController(new SpongePlayersController());
        Controllers.setChatController(new SpongeChatController());
        Controllers.setEntityController(new SpongeEntityController());

        WebServerBuilder webServerBuilder = new WebServerBuilder();

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
}
