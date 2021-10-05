package ru.redguy.webinfo.common.pages.player;

import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.structures.Location;
import ru.redguy.webinfo.common.utils.Controllers;
import ru.redguy.webinfo.common.utils.Response;

import java.util.UUID;

@WebPage(url = "/player/teleport/")
public class Teleport implements IWebPage {
    @Override
    public Response getPage(NanoHTTPD.IHTTPSession session) throws Exception {
        if(!session.getParameters().containsKey("uuid"))
            return Response.TheVariableIsNotPassed("uuid");
        UUID uuid = UUID.fromString(session.getParameters().get("uuid").get(0));

        if(!session.getParameters().containsKey("location"))
            return Response.TheVariableIsNotPassed("location");
        Location location = new Location(session.getParameters().get("location").get(0));

        return Response.OK(Controllers.getPlayersController().teleport(uuid,location));
    }
}
