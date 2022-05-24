package ru.redguy.webinfo.common.pages.player;

import fi.iki.elonen.NanoHTTPD;
import org.jetbrains.annotations.NotNull;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.QueryArgument;
import ru.redguy.webinfo.common.QueryArgumentType;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.controllers.Controllers;
import ru.redguy.webinfo.common.structures.Location;
import ru.redguy.webinfo.common.utils.Request;
import ru.redguy.webinfo.common.utils.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@WebPage(url = "/player/teleport/", args = {
        @QueryArgument(name = "uuid", type = QueryArgumentType.UUID),
        @QueryArgument(name = "location", type = QueryArgumentType.LOCATION)
}, method = NanoHTTPD.Method.POST)
public class Teleport implements IWebPage {
    @Override
    public Response getPage(Request req, @NotNull HashMap<String, ArrayList<Object>> args) throws Exception {
        UUID uuid = (UUID) args.get("uuid").get(0);
        Location location = (Location) args.get("location").get(0);

        return Response.OK(Controllers.getPlayersController().teleport(uuid, location).get());
    }
}
