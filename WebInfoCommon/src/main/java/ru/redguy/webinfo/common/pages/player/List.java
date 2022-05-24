package ru.redguy.webinfo.common.pages.player;

import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.controllers.Controllers;
import ru.redguy.webinfo.common.structures.NameUUIDPair;
import ru.redguy.webinfo.common.utils.Request;
import ru.redguy.webinfo.common.utils.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

@WebPage(url = "/player/list/")
public class List implements IWebPage {
    @Override
    public Response getPage(Request req, HashMap<String, ArrayList<Object>> args) throws Exception {
        return Response.OK(Controllers.getPlayersController().getPlayersList().stream().map(player -> new NameUUIDPair(player.getName(), player.getUuid())).collect(Collectors.toList()));
    }
}
