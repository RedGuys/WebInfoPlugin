package ru.redguy.webinfo.common.pages;

import fi.iki.elonen.NanoHTTPD;
import org.jetbrains.annotations.NotNull;
import ru.redguy.miniwebserver.utils.*;
import ru.redguy.miniwebserver.utils.arguments.StringArgument;
import ru.redguy.miniwebserver.utils.arguments.UUIDArgument;
import ru.redguy.webinfo.common.controllers.Controllers;
import ru.redguy.webinfo.common.structures.ActionResult;
import ru.redguy.webinfo.common.structures.Location;
import ru.redguy.webinfo.common.structures.NameUUIDPair;
import ru.redguy.webinfo.common.utils.LocationArgument;
import ru.redguy.webinfo.common.utils.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Router
public class Player {
    @WebPage(value = "/player/ban/", method = NanoHTTPD.Method.POST, args = {
            @QueryArgument(name = "uuid", type = UUIDArgument.class),
            @QueryArgument(name = "reason", type = StringArgument.class, required = false)
    })
    public void ban(@NotNull WebRequest req, WebResponse res) throws ExecutionException, InterruptedException {
        List<ActionResult> result = new ArrayList<>();
        for (Object o : req.getArguments().get("uuid")) {
            UUID uuid = (UUID) o;
            if (req.getArguments().get("reason").size() > 0) {
                result.add(Controllers.getPlayersController().ban(uuid, (String) req.getArguments().get("reason").get(0)).get());
            } else {
                result.add(Controllers.getPlayersController().ban(uuid).get());
            }
        }

        res.setResponse(result);
    }

    @WebPage(value = "/player/banip/", args = {
            @QueryArgument(name = "ip", type = StringArgument.class)
    }, method = NanoHTTPD.Method.POST)
    public void banIp(@NotNull WebRequest req, WebResponse res) throws Exception {
        List<ActionResult> result = new ArrayList<>();
        for (Object ip : req.getArguments().get("ip")) {
            result.add(Controllers.getPlayersController().banIp((String) ip).get());
        }
        res.setResponse(result);
    }

    @WebPage(value = "/player", args = {
            @QueryArgument(name = "uuid", type = UUIDArgument.class)
    })
    public void get(@NotNull WebRequest req, WebResponse res) throws Exception {
        List<ru.redguy.webinfo.common.structures.Player> result = new ArrayList<>();
        for (Object uuid : req.getArguments().get("uuid")) {
            ru.redguy.webinfo.common.structures.Player player = Controllers.getPlayersController().getPlayerInfo((UUID) uuid);
            result.add(player);
        }
        res.setResponse(result);
    }

    @WebPage(value = "/player/kick/", args = {
            @QueryArgument(name = "uuid", type = UUIDArgument.class),
            @QueryArgument(name = "reason", type = StringArgument.class, required = false)
    }, method = NanoHTTPD.Method.POST)
    public void kick(@NotNull WebRequest req, WebResponse res) throws Exception {
        List<ActionResult> result = new ArrayList<>();
        for (Object o : req.getArguments().get("uuid")) {
            if (req.getArguments().get("reason").size() > 0) {
                result.add(Controllers.getPlayersController().kick((UUID) o, (String) req.getArguments().get("reason").get(0)).get());
            } else {
                result.add(Controllers.getPlayersController().kick((UUID) o).get());
            }
        }
        res.setResponse(result);
    }

    @WebPage(value = "/player/list/")
    public void list(WebRequest req, @NotNull WebResponse res) throws Exception {
        res.setResponse(Controllers.getPlayersController().getPlayersList().stream().map(player -> new NameUUIDPair(player.getName(), player.getUuid())).collect(Collectors.toList()));
    }

    @WebPage(value = "/player/teleport/", args = {
            @QueryArgument(name = "uuid", type = UUIDArgument.class),
            @QueryArgument(name = "location", type = LocationArgument.class)
    }, method = NanoHTTPD.Method.POST)
    public void tp(@NotNull WebRequest req, @NotNull WebResponse res) throws Exception {
        UUID uuid = (UUID) req.getArguments().get("uuid").get(0);
        Location location = (Location) req.getArguments().get("location").get(0);
        if(location.getWorld()==null) {
            location.setWorld(Controllers.getPlayersController().getPlayerInfo(uuid).getLocation().getWorld());
        }
        res.setResponse(Controllers.getPlayersController().teleport(uuid, location).get());
    }
}
