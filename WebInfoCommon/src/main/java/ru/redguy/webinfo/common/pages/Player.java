package ru.redguy.webinfo.common.pages;

import ru.redguy.jrweb.Context;
import ru.redguy.jrweb.annotations.Page;
import ru.redguy.jrweb.annotations.Param;
import ru.redguy.jrweb.annotations.Router;
import ru.redguy.webinfo.common.controllers.Controllers;
import ru.redguy.webinfo.common.structures.Location;
import ru.redguy.webinfo.common.structures.NameUUIDPair;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Router("/player")
public class Player {
    @Page(value = "/ban", method = "POST")
    public void ban(Context ctx, @Param("uuid") UUID uuid, @Param(value = "reason", required = false) String reason) throws ExecutionException, InterruptedException {
        if (reason != null) {
            ctx.response.send(Controllers.getPlayersController().ban(uuid, reason));
        } else {
            ctx.response.send(Controllers.getPlayersController().ban(uuid));
        }
    }

    @Page(value = "/banip", method = "POST")
    public void banIp(Context ctx, @Param("ip") String ip) throws Exception {
        ctx.response.send(Controllers.getPlayersController().banIp(ip));
    }

    @Page(value = "/")
    public void get(Context ctx, @Param("uuid") UUID uuid) throws Exception {
        ctx.response.send(Controllers.getPlayersController().getPlayerInfo(uuid));
    }

    @Page(value = "/kick", method = "POST")
    public void kick(Context ctx, @Param("uuid") UUID uuid, @Param(value = "reason", required = false) String reason) throws Exception {
        if (reason != null) {
            ctx.response.send(Controllers.getPlayersController().kick(uuid, reason));
        } else {
            ctx.response.send(Controllers.getPlayersController().kick(uuid));
        }
    }

    @Page(value = "/list")
    public void list(Context ctx) throws Exception {
        ctx.response.send(Controllers.getPlayersController().getPlayersList().stream().map(player -> new NameUUIDPair(player.getName(), player.getUuid())).collect(Collectors.toList()));
    }

    @Page(value = "/teleport", method = "POST")
    public void tp(Context ctx, @Param("uuid") UUID uuid, @Param("location") Location location) throws Exception {
        if (location.getWorld() == null) {
            location.setWorld(Controllers.getPlayersController().getPlayerInfo(uuid).getLocation().getWorld());
        }
        ctx.response.send(Controllers.getPlayersController().teleport(uuid, location));
    }
}
