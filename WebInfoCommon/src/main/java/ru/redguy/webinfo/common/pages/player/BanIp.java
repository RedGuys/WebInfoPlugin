package ru.redguy.webinfo.common.pages.player;

import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.QueryArgument;
import ru.redguy.webinfo.common.QueryArgumentType;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.structures.ActionResult;
import ru.redguy.webinfo.common.controllers.Controllers;
import ru.redguy.webinfo.common.utils.Response;

import java.util.ArrayList;
import java.util.HashMap;

@WebPage(url = "/player/banip/", args = {
        @QueryArgument(name = "ip", type = QueryArgumentType.STRING)
}, method = NanoHTTPD.Method.POST)
public class BanIp implements IWebPage {
    @Override
    public Response getPage(NanoHTTPD.IHTTPSession session, HashMap<String, ArrayList<Object>> args) throws Exception {
        String ip = (String) args.get("ip").get(0);

        ActionResult result = Controllers.getPlayersController().banIp(ip).get();

        return Response.OK(result);
    }
}
