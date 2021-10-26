package ru.redguy.webinfo.common.pages;

import com.google.gson.JsonObject;
import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.structures.Player;
import ru.redguy.webinfo.common.utils.Controllers;
import ru.redguy.webinfo.common.utils.GSON;
import ru.redguy.webinfo.common.utils.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

@WebPage(url = "/")
public class Index implements IWebPage {
    public Response getPage(NanoHTTPD.IHTTPSession session, HashMap<String, ArrayList<Object>> args) throws IOException {
        JsonObject object = new JsonObject();
        object.addProperty("mine_version", Controllers.getBasicController().getMCVersion());
        object.add("players", GSON.gson.toJsonTree(Controllers.getPlayersController().getPlayersList().stream().map(Player::getName).collect(Collectors.toList())));
        object.addProperty("is_client", Controllers.getBasicController().isClient());
        object.addProperty("platform", Controllers.getBasicController().getPlatform());
        return Response.OK(object);
    }
}
