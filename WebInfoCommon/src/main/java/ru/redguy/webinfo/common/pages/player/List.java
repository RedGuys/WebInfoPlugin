package ru.redguy.webinfo.common.pages.player;

import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.utils.Controllers;
import ru.redguy.webinfo.common.utils.NameUUIDPair;
import ru.redguy.webinfo.common.utils.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

@WebPage(url="/player/list/")
public class List implements IWebPage {
    @Override
    public Response getPage(NanoHTTPD.IHTTPSession session, HashMap<String, ArrayList<Object>> args) throws Exception {
        return Response.OK(Controllers.getPlayersController().getPlayersList().stream().map(player -> new NameUUIDPair(player.getName(), player.getUuid())).collect(Collectors.toList()));
    }
}
