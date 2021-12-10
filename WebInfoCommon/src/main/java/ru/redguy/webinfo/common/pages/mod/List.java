package ru.redguy.webinfo.common.pages.mod;

import com.google.gson.JsonObject;
import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.structures.Mod;
import ru.redguy.webinfo.common.controllers.Controllers;
import ru.redguy.webinfo.common.utils.GSON;
import ru.redguy.webinfo.common.utils.Response;

import java.util.ArrayList;
import java.util.HashMap;

@WebPage(url="/mod/list/")
public class List implements IWebPage {
    @Override
    public Response getPage(NanoHTTPD.IHTTPSession session, HashMap<String, ArrayList<Object>> args) throws Exception {
        JsonObject object = new JsonObject();
        java.util.List<Mod> mods = Controllers.getBasicController().getModsList();
        object.addProperty("count", mods.size());
        object.add("mods", GSON.gson.toJsonTree(mods));
        return Response.OK(object);
    }
}
