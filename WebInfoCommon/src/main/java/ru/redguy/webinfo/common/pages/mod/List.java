package ru.redguy.webinfo.common.pages.mod;

import com.google.gson.JsonObject;
import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.structures.Mod;
import ru.redguy.webinfo.common.utils.Controllers;
import ru.redguy.webinfo.common.utils.GSON;
import ru.redguy.webinfo.common.utils.Response;

@WebPage(url="/mod/list/")
public class List implements IWebPage {
    @Override
    public Response getPage(NanoHTTPD.IHTTPSession session) throws Exception {
        JsonObject object = new JsonObject();
        java.util.List<Mod> mods = Controllers.getBasicController().getModsList();
        object.addProperty("count", mods.size());
        object.add("mods", GSON.gson.toJsonTree(mods));
        return Response.OK(object);
    }
}
