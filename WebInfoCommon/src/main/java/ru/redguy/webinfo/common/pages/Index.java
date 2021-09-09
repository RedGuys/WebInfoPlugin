package ru.redguy.webinfo.common.pages;

import com.google.gson.JsonObject;
import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.utils.GSON;
import ru.redguy.webinfo.common.utils.InfoUtils;

import java.io.IOException;

@WebPage(url = "/")
public class Index implements IWebPage {
    public NanoHTTPD.Response getPage(NanoHTTPD.IHTTPSession session) throws IOException {
        JsonObject object = new JsonObject();
        object.addProperty("MineV", InfoUtils.getInstance().getMCVersion());
        object.add("Players", GSON.gson.toJsonTree(InfoUtils.getInstance().getPlayersList()));
        object.addProperty("isClient",InfoUtils.getInstance().isClient());
        return NanoHTTPD.newFixedLengthResponse(object.toString());
    }
}
