package ru.redguy.webinfo.common.pages.worlds;

import com.google.gson.JsonObject;
import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfo.common.utils.GSON;
import ru.redguy.webinfo.common.utils.InfoUtils;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.structures.World;

import java.io.IOException;
import java.util.List;

@WebPage(url="/worlds/")
public class Worlds implements IWebPage {
    @Override
    public NanoHTTPD.Response getPage(NanoHTTPD.IHTTPSession session) throws IOException {
        JsonObject object = new JsonObject();
        List<World> worlds = InfoUtils.getInstance().getWorldsList();
        object.addProperty("count", worlds.size());
        object.add("worlds", GSON.gson.toJsonTree(worlds));
        return NanoHTTPD.newFixedLengthResponse(object.toString());
    }
}