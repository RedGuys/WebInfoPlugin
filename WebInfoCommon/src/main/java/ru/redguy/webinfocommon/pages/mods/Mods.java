package ru.redguy.webinfocommon.pages.mods;

import com.google.gson.JsonObject;
import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfocommon.IWebPage;
import ru.redguy.webinfocommon.WebPage;
import ru.redguy.webinfocommon.structures.Mod;
import ru.redguy.webinfocommon.utils.GSON;
import ru.redguy.webinfocommon.utils.InfoUtils;

import java.io.IOException;
import java.util.List;

@WebPage(url="/mods/")
public class Mods implements IWebPage {
    @Override
    public NanoHTTPD.Response getPage(NanoHTTPD.IHTTPSession session) throws IOException {
        JsonObject object = new JsonObject();
        List<Mod> mods = InfoUtils.getInstance().getModsList();
        object.addProperty("count", mods.size());
        object.add("mods", GSON.gson.toJsonTree(mods));
        return NanoHTTPD.newFixedLengthResponse(object.toString());
    }
}
