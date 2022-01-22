package ru.redguy.webinfo.common.pages.entity;

import com.google.gson.JsonObject;
import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.QueryArgument;
import ru.redguy.webinfo.common.QueryArgumentType;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.controllers.Controllers;
import ru.redguy.webinfo.common.structures.Location;
import ru.redguy.webinfo.common.structures.Player;
import ru.redguy.webinfo.common.utils.GSON;
import ru.redguy.webinfo.common.utils.Request;
import ru.redguy.webinfo.common.utils.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@WebPage(url = "/entity/spawn", method = NanoHTTPD.Method.POST, args = {
        @QueryArgument(name = "type", type = QueryArgumentType.STRING, required = true),
        @QueryArgument(name = "location", type = QueryArgumentType.LOCATION, required = true)
})
public class Spawn implements IWebPage {
    public Response getPage(Request req, HashMap<String, ArrayList<Object>> args) throws IOException, ExecutionException, InterruptedException {
        return Response.OK(Controllers.getEntityController().spawnEntity((String) args.get("type").get(0), (Location) args.get("location").get(0)).get());
    }
}
