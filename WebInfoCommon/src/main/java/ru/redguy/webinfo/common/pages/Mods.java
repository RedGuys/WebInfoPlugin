package ru.redguy.webinfo.common.pages;

import com.google.gson.JsonObject;
import ru.redguy.jrweb.Context;
import ru.redguy.jrweb.annotations.Page;
import ru.redguy.jrweb.annotations.Router;
import ru.redguy.jrweb.utils.optional.GsonUtil;
import ru.redguy.webinfo.common.controllers.Controllers;
import ru.redguy.webinfo.common.structures.Mod;

@Router("/mod")
public class Mods {
    @Page("/list")
    public void list(Context ctx) {
        JsonObject object = new JsonObject();
        java.util.List<Mod> mods = Controllers.getBasicController().getModsList();
        object.addProperty("count", mods.size());
        object.add("mods", GsonUtil.getGson().toJsonTree(mods));
        ctx.response.send(object);
    }
}
