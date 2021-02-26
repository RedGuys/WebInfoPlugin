package ru.redguy.webinfosponge;

import com.google.inject.Inject;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Plugin;
import ru.redguy.webinfocommon.UsersConfig;
import ru.redguy.webinfocommon.WebServer;
import ru.redguy.webinfocommon.utils.*;
import ru.redguy.webinfosponge.utils.SpongeInfoUtils;
import ru.redguy.webinfosponge.utils.SpongeLogger;

import java.io.IOException;

@Plugin(
        id = "webinfosponge",
        name = "WebInfoSponge",
        description = "This plugin allows you to view data about your server, as well as manage it by authorization. Change game settings, execute commands, and much more.",
        url = "https://redguy.ru",
        authors = {
                "RedGuy"
        }
)
public class WebInfoSponge {

    @Inject
    private org.slf4j.Logger logger;

    public WebServer webServer;

    @Listener
    public void onServerStart(GameStartedServerEvent event) throws IOException {
        Logger.InjectLogger(new SpongeLogger(logger));
        Config.InjectConfig(new SpongeConfig());
        Config.save();
        Language.initLanguage("config/WebInfo");
        Language.save();
        BasePlaceholders.registerBase();
        UsersConfig.loadUsers("config/WebInfo");
        InfoUtils.InjectInfoUtils(new SpongeInfoUtils());
        webServer = new WebServer(Config.getInt("web.port"));
    }
}
