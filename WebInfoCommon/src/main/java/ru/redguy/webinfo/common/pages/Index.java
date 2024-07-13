package ru.redguy.webinfo.common.pages;

import com.google.gson.JsonObject;
import ru.redguy.jrweb.Context;
import ru.redguy.jrweb.annotations.Page;
import ru.redguy.jrweb.annotations.Router;
import ru.redguy.jrweb.utils.optional.GsonUtil;
import ru.redguy.webinfo.common.WebInfoCommon;
import ru.redguy.webinfo.common.controllers.Controllers;
import ru.redguy.webinfo.common.structures.ActionResult;
import ru.redguy.webinfo.common.structures.Player;

import java.util.Map;
import java.util.stream.Collectors;

@Router("")
public class Index {

    @Page("/")
    public void index(Context ctx) {
        JsonObject object = new JsonObject();
        object.addProperty("mine_version", Controllers.getBasicController().getMCVersion());
        object.add("players", GsonUtil.getGson().toJsonTree(Controllers.getPlayersController().getPlayersList().stream().map(Player::getName).collect(Collectors.toList())));
        object.addProperty("is_client", Controllers.getBasicController().isClient());
        object.addProperty("platform", Controllers.getBasicController().getPlatform());
        object.addProperty("tps", Controllers.getBasicController().getTPS());
        ctx.response.send(object.toString());
    }

    @Page("/endpoints/")
    public void endpoints(Context ctx) {
        ctx.response.send(GsonUtil.getGson().toJson(WebInfoCommon.server.getPages().stream().map(Map.Entry::getKey).collect(Collectors.toList())));
    }

    @Page(value="/shutdown", method = "POST")
    public void shutdown(Context ctx) {
        Controllers.getBasicController().shutdown();
        ctx.response.send(new ActionResult(true));
    }
}
