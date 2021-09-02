package ru.redguy.webinfocommon.pages.worlds;

import com.google.gson.JsonObject;
import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfocommon.IWebPage;
import ru.redguy.webinfocommon.WebPage;
import ru.redguy.webinfocommon.structures.World;
import ru.redguy.webinfocommon.utils.GSON;
import ru.redguy.webinfocommon.utils.InfoUtils;

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