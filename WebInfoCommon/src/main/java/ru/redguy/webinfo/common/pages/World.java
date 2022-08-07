package ru.redguy.webinfo.common.pages;

import com.google.gson.JsonObject;
import fi.iki.elonen.NanoHTTPD;
import org.jetbrains.annotations.NotNull;
import ru.redguy.miniwebserver.utils.*;
import ru.redguy.miniwebserver.utils.arguments.BooleanArgument;
import ru.redguy.miniwebserver.utils.arguments.StringArgument;
import ru.redguy.miniwebserver.utils.arguments.UUIDArgument;
import ru.redguy.webinfo.common.controllers.Controllers;
import ru.redguy.webinfo.common.structures.ActionResult;
import ru.redguy.webinfo.common.utils.Response;
import ru.redguy.webinfo.common.utils.WorldArgument;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@Router("/world")
public class World {
    @WebPage(value = "/list")
    public void list(@NotNull WebRequest req, @NotNull WebResponse res) {
        JsonObject object = new JsonObject();
        java.util.List<ru.redguy.webinfo.common.structures.World> worlds = Controllers.getBasicController().getWorldsList();
        object.addProperty("count", worlds.size());
        object.add("worlds", GSON.gson.toJsonTree(worlds));
        res.setResponse(object);
    }

    @WebPage(value = "/unload", args = {
            @QueryArgument(name = "world", type = WorldArgument.class),
            @QueryArgument(name = "save", type = BooleanArgument.class, required = false)
    }, method = NanoHTTPD.Method.POST)
    public void getPage(@NotNull WebRequest req, WebResponse res) throws Exception {
        String worldName = (String) req.getArguments().get("world").get(0);

        boolean save = true;
        if (req.getArguments().get("save").size() > 0)
            save = (boolean) req.getArguments().get("save").get(0);

        ActionResult result = Controllers.getWorldsController().unloadWorld(worldName, save).get();

        res.setResponse(result);
    }
}
