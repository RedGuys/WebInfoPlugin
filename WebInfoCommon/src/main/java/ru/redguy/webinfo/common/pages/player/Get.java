package ru.redguy.webinfo.common.pages.player;

import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.QueryArgument;
import ru.redguy.webinfo.common.QueryArgumentType;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.structures.Player;
import ru.redguy.webinfo.common.controllers.Controllers;
import ru.redguy.webinfo.common.utils.Request;
import ru.redguy.webinfo.common.utils.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@WebPage(url="/player", args = {
        @QueryArgument(name = "uuid", type = QueryArgumentType.STRING)
})
public class Get implements IWebPage {
    @Override
    public Response getPage(Request req, HashMap<String, ArrayList<Object>> args) throws Exception {
        UUID uuid = (UUID) args.get("uuid").get(0);
        Player player = Controllers.getPlayersController().getPlayerInfo(uuid);
        if(player == null)
            return Response.NotFound();
        return Response.OK(player);
    }
}