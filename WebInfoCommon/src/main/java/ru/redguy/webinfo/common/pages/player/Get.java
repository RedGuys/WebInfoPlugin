package ru.redguy.webinfo.common.pages.player;

import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.structures.Player;
import ru.redguy.webinfo.common.utils.Controllers;
import ru.redguy.webinfo.common.utils.Response;

import java.util.UUID;

@WebPage(url="/player/get/")
public class Get implements IWebPage {
    @Override
    public Response getPage(NanoHTTPD.IHTTPSession session) throws Exception {
        if(!session.getParameters().containsKey("uuid"))
            return Response.TheVariableIsNotPassed("uuid");
        UUID uuid = UUID.fromString(session.getParameters().get("uuid").get(0));
        Player player = Controllers.getPlayersController().getPlayerInfo(uuid);
        if(player == null)
            return Response.NotFound();
        return Response.OK(player);
    }
}