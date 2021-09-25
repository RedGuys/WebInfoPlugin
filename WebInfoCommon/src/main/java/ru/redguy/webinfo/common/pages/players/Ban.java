package ru.redguy.webinfo.common.pages.players;

import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.utils.ActionResult;
import ru.redguy.webinfo.common.utils.Controllers;
import ru.redguy.webinfo.common.utils.Response;

import java.util.UUID;

@WebPage(url = "/players/ban/")
public class Ban implements IWebPage {
    @Override
    public Response getPage(NanoHTTPD.IHTTPSession session) throws Exception {
        if(!session.getParameters().containsKey("uuid"))
            return Response.TheVariableIsNotPassed("uuid");
        UUID uuid = UUID.fromString(session.getParameters().get("uuid").get(0));

        ActionResult result;
        if(session.getParameters().containsKey("reason")) {
            result = Controllers.getPlayersController().ban(uuid,session.getParameters().get("reason").get(0)).get();
        } else {
            result = Controllers.getPlayersController().ban(uuid).get();
        }

        return Response.OK(result);
    }
}
