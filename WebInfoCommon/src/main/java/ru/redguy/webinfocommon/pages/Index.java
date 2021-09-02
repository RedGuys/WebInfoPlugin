package ru.redguy.webinfocommon.pages;

import com.google.gson.JsonObject;
import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfocommon.IWebPage;
import ru.redguy.webinfocommon.WebPage;
import ru.redguy.webinfocommon.utils.GSON;
import ru.redguy.webinfocommon.utils.InfoUtils;

import java.io.IOException;

@WebPage(url = "/")
public class Index implements IWebPage {
    public NanoHTTPD.Response getPage(NanoHTTPD.IHTTPSession session) throws IOException {
        JsonObject object = new JsonObject();
        object.addProperty("MineV", InfoUtils.getInstance().getMCVersion());
        object.add("Players",GSON.gson.toJsonTree(InfoUtils.getInstance().getPlayersList()));
        object.addProperty("isClient",InfoUtils.getInstance().isClient());
        return NanoHTTPD.newFixedLengthResponse(object.toString());
    }
}
