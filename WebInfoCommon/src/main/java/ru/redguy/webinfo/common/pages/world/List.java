package ru.redguy.webinfo.common.pages.world;

import com.google.gson.JsonObject;
import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.structures.World;
import ru.redguy.webinfo.common.utils.Controllers;
import ru.redguy.webinfo.common.utils.GSON;
import ru.redguy.webinfo.common.utils.Response;

@WebPage(url="/world/list/")
public class List implements IWebPage {
    @Override
    public Response getPage(NanoHTTPD.IHTTPSession session) throws Exception {
        JsonObject object = new JsonObject();
        java.util.List<World> worlds = Controllers.getBasicController().getWorldsList();
        object.addProperty("count", worlds.size());
        object.add("worlds", GSON.gson.toJsonTree(worlds));
        return Response.OK(object);
    }
}