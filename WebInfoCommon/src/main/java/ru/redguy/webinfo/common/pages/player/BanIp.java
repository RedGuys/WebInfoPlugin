package ru.redguy.webinfo.common.pages.player;

import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.utils.ActionResult;
import ru.redguy.webinfo.common.utils.Controllers;
import ru.redguy.webinfo.common.utils.Response;

@WebPage(url = "/player/banip/")
public class BanIp implements IWebPage {
    @Override
    public Response getPage(NanoHTTPD.IHTTPSession session) throws Exception {
        if(!session.getParameters().containsKey("ip"))
            return Response.TheVariableIsNotPassed("ip");
        String ip = session.getParameters().get("ip").get(0);

        ActionResult result = Controllers.getPlayersController().banIp(ip).get();

        return Response.OK(result);
    }
}
