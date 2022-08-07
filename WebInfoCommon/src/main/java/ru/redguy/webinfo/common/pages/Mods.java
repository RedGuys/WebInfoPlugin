package ru.redguy.webinfo.common.pages;

import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;
import ru.redguy.miniwebserver.utils.*;
import ru.redguy.webinfo.common.controllers.Controllers;
import ru.redguy.webinfo.common.structures.Mod;

@Router
public class Mods {
    @WebPage("/mod/list/")
    public void list(WebRequest req, @NotNull WebResponse res) {
        JsonObject object = new JsonObject();
        java.util.List<Mod> mods = Controllers.getBasicController().getModsList();
        object.addProperty("count", mods.size());
        object.add("mods", GSON.gson.toJsonTree(mods));
        res.setResponse(object);
    }
}
