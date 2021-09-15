package ru.redguy.webinfo.common.pages.mods;

import com.google.gson.JsonObject;
import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.structures.Mod;
import ru.redguy.webinfo.common.utils.GSON;
import ru.redguy.webinfo.common.utils.InfoUtils;
import ru.redguy.webinfo.common.utils.Response;

import java.io.IOException;
import java.util.List;

@WebPage(url="/mods/")
public class Mods implements IWebPage {
    @Override
    public Response getPage(NanoHTTPD.IHTTPSession session) throws IOException {
        JsonObject object = new JsonObject();
        List<Mod> mods = InfoUtils.getInstance().getModsList();
        object.addProperty("count", mods.size());
        object.add("mods", GSON.gson.toJsonTree(mods));
        return Response.OK(object);
    }
}
