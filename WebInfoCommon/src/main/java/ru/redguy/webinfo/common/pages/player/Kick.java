package ru.redguy.webinfo.common.pages.player;

import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.QueryArgument;
import ru.redguy.webinfo.common.QueryArgumentType;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.structures.ActionResult;
import ru.redguy.webinfo.common.controllers.Controllers;
import ru.redguy.webinfo.common.utils.Request;
import ru.redguy.webinfo.common.utils.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@WebPage(url = "/player/kick/", args = {
        @QueryArgument(name = "uuid", type = QueryArgumentType.UUID),
        @QueryArgument(name = "reason", type = QueryArgumentType.STRING, required = false)
}, method = NanoHTTPD.Method.POST)
public class Kick implements IWebPage {
    @Override
    public Response getPage(Request req, HashMap<String, ArrayList<Object>> args) throws Exception {
        UUID uuid = (UUID) args.get("uuid").get(0);

        ActionResult result;
        if(args.get("reason").size() > 0) {
            result = Controllers.getPlayersController().kick(uuid, (String) args.get("reason").get(0)).get();
        } else {
            result = Controllers.getPlayersController().kick(uuid).get();
        }

        return Response.OK(result);
    }
}
