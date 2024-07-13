package ru.redguy.webinfo.common.pages;

import com.google.gson.JsonObject;
import ru.redguy.jrweb.Context;
import ru.redguy.jrweb.annotations.Page;
import ru.redguy.jrweb.annotations.Param;
import ru.redguy.jrweb.annotations.Router;
import ru.redguy.jrweb.utils.optional.GsonUtil;
import ru.redguy.webinfo.common.controllers.Controllers;

import java.util.concurrent.ExecutionException;


@Router("/world")
public class World {
    @Page(value = "/list")
    public void list(Context ctx) throws ExecutionException, InterruptedException {
        JsonObject object = new JsonObject();
        java.util.List<ru.redguy.webinfo.common.structures.World> worlds = Controllers.getBasicController().getWorldsList().get();
        object.addProperty("count", worlds.size());
        object.add("worlds", GsonUtil.getGson().toJsonTree(worlds));
        ctx.response.send(object);
    }

    @Page(value = "/unload", method = "POST")
    public void getPage(Context ctx, @Param("world") String world, @Param(value = "save", required = false) Boolean save) throws Exception {
        ctx.response.send(Controllers.getWorldsController().unloadWorld(world, save == null || save).get());
    }
}
