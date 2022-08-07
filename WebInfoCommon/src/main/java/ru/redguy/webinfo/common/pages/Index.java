package ru.redguy.webinfo.common.pages;

import com.google.gson.JsonObject;
import fi.iki.elonen.NanoHTTPD;
import org.jetbrains.annotations.NotNull;
import ru.redguy.miniwebserver.utils.*;
import ru.redguy.webinfo.common.WebInfoCommon;
import ru.redguy.webinfo.common.controllers.Controllers;
import ru.redguy.webinfo.common.structures.ActionResult;
import ru.redguy.webinfo.common.structures.Player;
import ru.redguy.webinfo.common.utils.Response;

import java.util.stream.Collectors;

@Router
public class Index {

    @WebPage("/")
    public void index(WebRequest req, @NotNull WebResponse res) {
        JsonObject object = new JsonObject();
        object.addProperty("mine_version", Controllers.getBasicController().getMCVersion());
        object.add("players", GSON.gson.toJsonTree(Controllers.getPlayersController().getPlayersList().stream().map(Player::getName).collect(Collectors.toList())));
        object.addProperty("is_client", Controllers.getBasicController().isClient());
        object.addProperty("platform", Controllers.getBasicController().getPlatform());
        object.addProperty("tps", Controllers.getBasicController().getTPS());
        res.setResponse(object);
    }

    @WebPage("/endpoints/")
    public void endpoints(WebRequest req, @NotNull WebResponse res) {
        res.setResponse(WebInfoCommon.server.getPages().keySet().stream().map(
                pair -> pair.getKey().name() + " " + pair.getValue()
        ).toArray());
    }

    @WebPage(value="/shutdown", method = NanoHTTPD.Method.POST)
    public void shutdown(WebRequest req, @NotNull WebResponse res) {
        Controllers.getBasicController().shutdown();
        res.setResponse(new ActionResult(true));
    }
}
