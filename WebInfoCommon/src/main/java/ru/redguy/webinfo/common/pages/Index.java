package ru.redguy.webinfo.common.pages;

import com.google.gson.JsonObject;
import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.utils.Controllers;
import ru.redguy.webinfo.common.utils.GSON;
import ru.redguy.webinfo.common.utils.Response;

import java.io.IOException;

@WebPage(url = "/")
public class Index implements IWebPage {
    public Response getPage(NanoHTTPD.IHTTPSession session) throws IOException {
        JsonObject object = new JsonObject();
        object.addProperty("mine_version", Controllers.getBasicController().getMCVersion());
        object.add("players", GSON.gson.toJsonTree(Controllers.getBasicController().getPlayersList()));
        object.addProperty("is_client", Controllers.getBasicController().isClient());
        object.addProperty("platform", Controllers.getBasicController().getPlatform());
        return Response.OK(object);
    }
}
