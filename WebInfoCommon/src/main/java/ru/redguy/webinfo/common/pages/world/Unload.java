package ru.redguy.webinfo.common.pages.world;

import fi.iki.elonen.NanoHTTPD;
import org.jetbrains.annotations.NotNull;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.QueryArgument;
import ru.redguy.webinfo.common.QueryArgumentType;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.controllers.Controllers;
import ru.redguy.webinfo.common.structures.ActionResult;
import ru.redguy.webinfo.common.utils.Request;
import ru.redguy.webinfo.common.utils.Response;

import java.util.ArrayList;
import java.util.HashMap;

@WebPage(url = "/world/unload/", args = {
        @QueryArgument(name = "world", type = QueryArgumentType.WORLD),
        @QueryArgument(name = "save", type = QueryArgumentType.BOOLEAN, required = false)
}, method = NanoHTTPD.Method.POST)
public class Unload implements IWebPage {
    @Override
    public Response getPage(Request req, @NotNull HashMap<String, ArrayList<Object>> args) throws Exception {
        String worldName = (String) args.get("world").get(0);

        boolean save = true;
        if (args.get("save").size() > 0)
            save = (boolean) args.get("save").get(0);

        ActionResult result = Controllers.getWorldsController().unloadWorld(worldName, save).get();

        return Response.OK(result);
    }
}
